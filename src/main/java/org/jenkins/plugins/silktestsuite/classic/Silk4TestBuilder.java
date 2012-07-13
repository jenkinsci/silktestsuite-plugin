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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.jenkins.plugins.silktestsuite.common.Utils;
import org.kohsuke.stapler.DataBoundConstructor;

import com.google.common.base.Strings;

public final class Silk4TestBuilder extends Builder {
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.classic");

  private final String testScript;
  private final String optionFile;
  private final String query;
  private final String configFile;
  private final long timeout;

  @DataBoundConstructor
  public Silk4TestBuilder(final String testScript, final String optionFile, final String configFile, final String query, final String timeout) {
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
  public boolean perform(final AbstractBuild<?, ?> build, final Launcher launcher, final BuildListener listener) throws InterruptedException, IOException {
    if (!Functions.isWindows()) {
      listener.error("Only available on Windows systems.");
      LOGGER.severe("This operation system is not supported with SilkTest.");
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

    Map<String,String> environment = launcher.getChannel().call(new RemoteEnvironmentVariables());
    
    List<String> cmd = createCommandLine(build, environment, findSilkTestClassicDriver(build));

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

  private String findSilkTestClassicDriver(AbstractBuild<?, ?> build) throws IOException, InterruptedException {
    File pluginDir = new File(Hudson.getInstance().getRootDir(), "plugin/silktestsuite");
    
    File[] listFiles = pluginDir.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.startsWith("silktestclassic") && name.endsWith(".jar");
      }
    });
    String root = new File(".").getAbsolutePath();    
    File localSilkTestDriver = listFiles != null ? listFiles[0] : new File(root.substring(0, root.lastIndexOf('.')),
        "src/main/webapp/silktestclassic-0.0.1-SNAPSHOT-jar-with-dependencies.jar"); // FIXME:
    // remove absolute path referencing the classic driver in case of debug mode
    
    FilePath remoteWorkspace = build.getWorkspace();
    new FilePath(localSilkTestDriver).copyTo(new FilePath(remoteWorkspace, "silktestclassic.jar"));
    
    FilePath[] list = remoteWorkspace.list("silktestclassic.jar");
    assert (list.length == 1);
    return list[0].getRemote();
  }

  private List<String> createCommandLine(final AbstractBuild<?, ?> build, Map<String,String> environment, String pathOfSilkTestClassicDriver) throws MalformedURLException, IOException,
      InterruptedException {
    String pathToWorkspace = build.getWorkspace().getRemote();

    List<String> cmd = new ArrayList<String>();
    cmd.add(findJavaHomeDirectory(environment) + "bin/java.exe");
    cmd.add("-cp");
    String segueHome = environment.get("SEGUE_HOME");
    cmd.add(pathOfSilkTestClassicDriver + ";" + segueHome + "ClassFiles\\stCtrl.jar;" + segueHome + "ClassFiles\\core.jar");
    cmd.add("at.tfuerer.silktest.classic.SilkTestClassicDriver");
    addOptionToCommandLine(cmd, "-t", pathToWorkspace, testScript);
    addOptionToCommandLine(cmd, "-o", pathToWorkspace, optionFile);
    if (Silk4TestBuilderDescriptor.isIniFile(configFile))
      addOptionToCommandLine(cmd, "-i", pathToWorkspace, configFile);
    else if (Silk4TestBuilderDescriptor.isProjectFile(configFile))
      addOptionToCommandLine(cmd, "-p", pathToWorkspace, configFile);
    else
      LOGGER.warning(MessageFormat.format("Specified configuration file [{0}] is not valid.", configFile));
    addOptionToCommandLine(cmd, "-r", pathToWorkspace, "SilkTestResults\\Classic");
    return cmd;
  }

  private String findJavaHomeDirectory(Map<String,String> environment) {
    String javaHome = environment.get("JAVA_HOME");
    if (Strings.isNullOrEmpty(javaHome))
      javaHome = searchJREInProgramFiles(environment, javaHome, "ProgramFiles");
    if (Strings.isNullOrEmpty(javaHome))
      javaHome = searchJREInProgramFiles(environment, javaHome, "ProgramFiles(x86)");
    if (Strings.isNullOrEmpty(javaHome))
      javaHome = "";

    return javaHome + "/";
  }

  private String searchJREInProgramFiles(Map<String,String> environment, String javaHome, String env) {
    File javaRoot = new File(environment.get(env) + "/java");
    String latestJRE = latestAvailableJavaRuntime(javaRoot, "jre");
    if (Strings.isNullOrEmpty(latestJRE))
      latestJRE = latestAvailableJavaRuntime(javaRoot, "jdk");
    javaHome = new File(javaRoot, latestJRE).getAbsolutePath();
    return javaHome;
  }

  private String latestAvailableJavaRuntime(File javaRoot, final String searchString) {
    List<String> runtimes = Arrays.asList(javaRoot.list(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        if (name.toLowerCase().startsWith(searchString.toLowerCase()))
          return true;
        return false;
      }
    }));
    
    Collections.sort(runtimes);
    return runtimes.get(runtimes.size()-1);
  }

  private void addOptionToCommandLine(final List<String> cmd, final String option, final String pathToWorkspace, final String optionValue) {
    if (!Strings.isNullOrEmpty(optionValue)) {
      cmd.add(option);
      cmd.add(pathToWorkspace + "\\"+ optionValue);
    }
  }
}
