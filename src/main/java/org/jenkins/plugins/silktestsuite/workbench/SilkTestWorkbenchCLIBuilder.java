package org.jenkins.plugins.silktestsuite.workbench;

import hudson.FilePath;
import hudson.Functions;
import hudson.Launcher;
import hudson.Launcher.ProcStarter;
import hudson.Proc;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.tasks.Builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.jenkins.plugins.silktestsuite.common.RemoteEnvironmentVariables;
import org.jenkins.plugins.silktestsuite.common.Utils;
import org.kohsuke.stapler.DataBoundConstructor;

import com.google.common.base.Strings;

public class SilkTestWorkbenchCLIBuilder extends Builder {
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.workbench");

  private final String user;
  private final String password;
  private final String dsn;
  private final String projectsCsv;
  private final String namesCsv;
  private final String playbackenv;

  @DataBoundConstructor
  public SilkTestWorkbenchCLIBuilder(final String user, final String password, final String dsn, final String projectsCsv,
      final String playbackenv, final String namesCsv) {
    this.user = user;
    this.password = password;
    this.dsn = dsn;
    this.projectsCsv = projectsCsv;
    this.playbackenv = StringUtils.isEmpty(playbackenv) ? "System Defaults" : playbackenv;
    this.namesCsv = namesCsv;
  }

  public String getPassword() {
    return password;
  }

  public String getUser() {
    return user;
  }

  public String getDsn() {
    return dsn;
  }

  public String getProjectsCsv() {
    return projectsCsv;
  }

  public String getNamesCsv() {
    return namesCsv;
  }

  @Override
  public boolean perform(final AbstractBuild<?, ?> build, final Launcher launcher, final BuildListener listener)
      throws InterruptedException, IOException {
    if (!Functions.isWindows()) {
      listener.error("Only available on Windows systems.");
      LOGGER.severe("This operation system is not supported with SilkTest.");
      build.setResult(Result.ABORTED);
      return true;
    }
    
    if (!Utils.cleanupWorkspace(launcher, build.getWorkspace().getRemote(), build.getTimestamp())) {
      listener.error("[SilkTest Workbench] Deleting the result folder failed.");
      build.setResult(Result.FAILURE);
      return false;
    }
    
    FilePath resultPath = createResultFolders(build.getWorkspace());
    String resultFileName = calculateResultName(dsn, projectsCsv, namesCsv);
    FilePath resultFile = new FilePath(resultPath, resultFileName+".txt");
    Map<String,String> environment = launcher.getChannel().call(new RemoteEnvironmentVariables());
    List<String> cmd = createCommandLine(environment, resultFile.getRemote());
    ProcStarter silktestworkbench = launcher.launch().cmds(cmd).stdout(listener.getLogger());
    Proc launch = launcher.launch(silktestworkbench);
    int exitCode = launch.join();
    Result result = exitCode == 0 ? Result.SUCCESS : Result.UNSTABLE;
    
    FilePath junitResultFile = new FilePath(resultPath, resultFileName+".xml");
    new STWTestResultConverter().convert(resultFile, junitResultFile);

    build.setResult(result);

    return result.isBetterThan(Result.FAILURE);
  }

  private String calculateResultName(String dsn2, String projectsCsv2, String namesCsv2) {
    return "xy";
  }

  private List<String> createCommandLine(Map<String, String> environment, String resultPath) {
    String oaHome = environment.get("OPEN_AGENT_HOME");
    List<String> cli = new ArrayList<String>();
    cli.add(oaHome+"/gui/stw.exe");
    cli.add("-u");
    cli.add(this.user);
    cli.add("-p");
    cli.add(password);
    cli.add("-d");
    cli.add(dsn);
    cli.add("-o");
    cli.add(resultPath);
    if (!Strings.isNullOrEmpty(projectsCsv)) {
      cli.add("-r");
      cli.add(projectsCsv); // FIXME: allows only one project
    }
    if (!Strings.isNullOrEmpty(namesCsv)) {
      cli.add("-s");
      cli.add(namesCsv); // FIXME: allows only one script name
    }
    if (!Strings.isNullOrEmpty(playbackenv)) {
      cli.add("-e");
      cli.add(playbackenv);
    }
    
    return cli;
  }

  private FilePath createResultFolders(final FilePath filePath) throws IOException, InterruptedException {
    final FilePath silktestResultRoot = new FilePath(filePath, "SilkTestResults");
    if (!silktestResultRoot.exists()) {
      silktestResultRoot.mkdirs();
    }

    final FilePath workbenchResults = new FilePath(silktestResultRoot, "Workbench");
    if (!workbenchResults.exists()) {
      workbenchResults.mkdirs();
    }
    return workbenchResults;
  }

}
