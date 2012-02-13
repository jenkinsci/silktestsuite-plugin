package org.jenkins.plugins.silktestsuite.classic;

import hudson.FilePath;
import hudson.Functions;
import hudson.Launcher;
import hudson.Launcher.ProcStarter;
import hudson.Proc;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.model.Hudson;
import hudson.tasks.Builder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.jenkins.plugins.silktestsuite.common.Utils;
import org.kohsuke.stapler.DataBoundConstructor;

import com.google.common.base.Strings;

public final class Silk4TestBuilder extends Builder {
  private static final String JAVA_HOME = System.getenv("JAVA_HOME");
  private static final String SEGUE_HOME = System.getenv("SEGUE_HOME");
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.classic");

  private final String testScript;
  private final String optionFile;
  private final String query;
  private final String configFile;
  private final long timeout;

  @DataBoundConstructor
  public Silk4TestBuilder(final String testScript, final String optionFile, final String configFile,
      final String query, final String timeout) {
    super();
    this.testScript = testScript;
    this.optionFile = optionFile;
    this.configFile = configFile;
    this.query = query;
    this.timeout = Long.parseLong(timeout);
  }

  public String getTestScript() {
    return testScript;
  }

  public String getOptionFile() {
    return optionFile;
  }

  public String getConfigFile() {
    return configFile;
  }

  public String getQuery() {
    return query;
  }

  public String getTimeout() {
    return String.valueOf(timeout);
  }

  @Override
  public Silk4TestBuilderDescriptor getDescriptor() {
    return (Silk4TestBuilderDescriptor) Hudson.getInstance().getDescriptor(getClass());
  }

  @Override
  public boolean perform(final AbstractBuild<?, ?> build, final Launcher launcher, final BuildListener listener)
      throws InterruptedException, IOException {
    if (!Functions.isWindows()) {
      listener.error("Only available on Windows systems.");
      LOGGER.severe("Windows is not supported for Silktest.");
      build.setResult(Result.ABORTED);
      return true;
    }

    if (!Utils.cleanupWorkspace(build.getWorkspace(), build.getTimestamp())) {
      build.setResult(Result.FAILURE);
      listener.error("[SilkTest Classic] Deleting the result folder failed.");
      LOGGER.severe("Deleting result folder failed.");
      return false;
    }

    listener.getLogger().println(MessageFormat.format("[SilkTest Classic] Run {0}.", testScript));

    FilePath workDir = new FilePath(Hudson.getInstance().getRootPath(), "plugin/silktestsuite");
    List<String> cmd = createCommandLine(build, workDir.toURI().toURL().getPath());

    ProcStarter silktestClassicDriver = launcher.launch().cmds(cmd).stdout(listener.getLogger());
    Proc launch = launcher.launch(silktestClassicDriver);
    int exitCode = launch.joinWithTimeout(timeout, TimeUnit.SECONDS, listener);

    switch (exitCode) {
    case 0:
      build.setResult(Result.SUCCESS);
      break;
    case 1:
      build.setResult(Result.FAILURE);
      break;
    case 2:
      build.setResult(Result.UNSTABLE);
      break;
    }
    build.setResult(exitCode == 0 ? Result.SUCCESS : Result.FAILURE);
    return true;
  }

  private List<String> createCommandLine(final AbstractBuild<?, ?> build, String pluginDirPath)
      throws MalformedURLException, IOException, InterruptedException {
    File pluginDir = new File(pluginDirPath);
    File[] listFiles = pluginDir.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.startsWith("silktestclassic") && name.endsWith(".jar");
      }
    });

    String pathOfSilkTestClassicDriver = listFiles != null ? listFiles[0].getAbsolutePath()
        : "G:/jenkinsSVN/silktestsuite/src/main/webapp/silktestclassic-0.0.1-SNAPSHOT-jar-with-dependencies.jar"; // FIXME:
    // remove absolute path referencing the classic driver in case of debug mode
    String pathToWorkspace = build.getWorkspace().toURI().getPath().replaceFirst("/", "");

    List<String> cmd = new ArrayList<String>();
    cmd.add(JAVA_HOME + "/bin/java.exe");
    cmd.add("-cp");
    cmd.add(pathOfSilkTestClassicDriver + ";" + SEGUE_HOME + "ClassFiles\\stCtrl.jar;" + SEGUE_HOME
        + "ClassFiles\\core.jar");
    cmd.add("at.tfuerer.silktest.classic.SilkTestClassicDriver");
    addOptionToCommandLine(cmd, "-t", pathToWorkspace, testScript);
    addOptionToCommandLine(cmd, "-o", pathToWorkspace, optionFile);
    if (Silk4TestBuilderDescriptor.isIniFile(configFile))
      addOptionToCommandLine(cmd, "-i", pathToWorkspace, configFile);
    else if (Silk4TestBuilderDescriptor.isProjectFile(configFile))
      addOptionToCommandLine(cmd, "-p", pathToWorkspace, configFile);
    else
      LOGGER.warning(MessageFormat.format("Specified configuration file [{0}] is not valid.", configFile));
    addOptionToCommandLine(cmd, "-r", pathToWorkspace, "/SilkTestResults/Classic");
    return cmd;
  }

  private void addOptionToCommandLine(final List<String> cmd, final String option, final String pathToWorkspace,
      final String optionValue) {
    if (!Strings.isNullOrEmpty(optionValue)) {
      cmd.add(option);
      cmd.add(pathToWorkspace + optionValue);
    }
  }
}
