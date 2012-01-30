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
import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;

import com.google.common.base.Strings;

public final class Silk4TestBuilder extends Builder {
  private static final String JAVA_HOME = System.getenv("JAVA_HOME");
  private static final String SEGUE_HOME = System.getenv("SEGUE_HOME");
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.classic");

  private final String testScript;
  private final String optionFile;
  private final String iniFile;
  private final String query;
  private final String projectFile;

  @DataBoundConstructor
  public Silk4TestBuilder(final String testScript, final String optionFile, final String projectFile,
      final String iniFile, final String query) {
    super();
    this.testScript = testScript;
    this.optionFile = optionFile;
    this.projectFile = projectFile;
    this.iniFile = iniFile;
    this.query = query;
  }

  public String getTestScript() {
    return testScript;
  }

  public String getOptionFile() {
    return optionFile;
  }

  public String getProjectFile() {
    return projectFile;
  }

  public String getIniFile() {
    return iniFile;
  }

  public String getQuery() {
    return query;
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
      build.setResult(Result.ABORTED);
      return true;
    }

    listener.getLogger().println(MessageFormat.format("[SilkTestSuite:Classic] Run {0}.", testScript));

    FilePath workDir = new FilePath(Hudson.getInstance().getRootPath(), "plugin/silktestsuite");
    List<String> cmd = createCommandLine(build, workDir.toURI().toURL().getPath());

    ProcStarter silktestClassicDriver = launcher.launch().cmds(cmd).stdout(listener.getLogger());
    Proc launch = launcher.launch(silktestClassicDriver);
    int exitCode = launch.join(); // TODO: use timeout

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
        : "G:/jenkinsSVN/silktestsuite/src/main/webapp/silktestclassic-0.0.1-SNAPSHOT-jar-with-dependencies.jar";

    List<String> cmd = new ArrayList<String>();
    cmd.add(JAVA_HOME + "/bin/java.exe");
    cmd.add("-cp");
    cmd.add(pathOfSilkTestClassicDriver + ";" + SEGUE_HOME + "ClassFiles\\stCtrl.jar;" + SEGUE_HOME
        + "ClassFiles\\core.jar");
    cmd.add("at.tfuerer.silktest.classic.SilkTestClassicDriver");
    addOptionToCommandLine(cmd, "-t", testScript);
    addOptionToCommandLine(cmd, "-o", optionFile);
    addOptionToCommandLine(cmd, "-p", projectFile);
    addOptionToCommandLine(cmd, "-i", iniFile);
    addOptionToCommandLine(cmd, "-r", build.getWorkspace().toURI().toURL().getPath() + "/SilkTestResults/Classic");
    return cmd;
  }

  private void addOptionToCommandLine(final List<String> cmd, final String option, final String optionValue) {
    if (!Strings.isNullOrEmpty(optionValue)) {
      cmd.add(option);
      cmd.add(optionValue);
    }
  }
}
