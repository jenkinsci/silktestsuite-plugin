package org.jenkins.plugins.silktestsuite.workbench;

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.tasks.Builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import com.borland.silktest.workbench.generated.ClassFactory;
import com.borland.silktest.workbench.generated.ISTWApp;
import com.borland.silktest.workbench.generated.ISTWDotNETScripts;
import com.borland.silktest.workbench.generated.ISTWExecutionResult;
import com.borland.silktest.workbench.generated.ISTWProjects;
import com.borland.silktest.workbench.generated.ISTWResult;
import com.borland.silktest.workbench.generated.ISTWVisualTests;
import com4j.ComException;
import com4j.Holder;

public class SilkTestWorkbenchBuilder extends Builder {
  private static final int WAIT_FOR_STWIDLE_TIMEOUT = 1000;
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.workbench");

  private final String user;
  private final String password;
  private final String dsn;
  private final String projectsCsv;
  private final String namesCsv;
  private final String playbackenv;

  @DataBoundConstructor
  public SilkTestWorkbenchBuilder(String user, String password, String dsn, String projectsCsv, String playbackenv,
      String namesCsv) {
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
  public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
      throws InterruptedException {
    try {
      deleteResults(build.getWorkspace(), build.getTimestamp());
    } catch (IOException e) {
      listener.error("[SilkTest Workbench] Deleting the result folder failed because of %s.", e.getLocalizedMessage());
      LOGGER.log(Level.SEVERE, "Deleting the result folder failed.", e);
      build.setResult(Result.FAILURE);
    }

    ISTWApp stWorkbench = ClassFactory.createSTWApp();
    if (!stWorkbench.login(user, password, dsn, Boolean.TRUE, Boolean.FALSE)) {
      listener.error("[SilkTest Workbench] Log-in at SilkTest Workbench failed.");
      LOGGER.warning("log in to Silktest Workbench failed.");
      build.setResult(Result.FAILURE);
      return false;
    }

    List<String> projectNames = new ArrayList<String>();
    ISTWProjects projects = stWorkbench.projects();
    if (StringUtils.isEmpty(projectsCsv)) {
      for (int i = 1; i <= projects.count(); i++) {
        projectNames.add(projects.item(i).name());
      }
    } else
      Collections.addAll(projectNames, StringUtils.splitByWholeSeparator(projectsCsv, ","));

    Result result = build.getResult() == null ? Result.SUCCESS : build.getResult();
    for (String projectName : projectNames) {
      List<String> testNames = new ArrayList<String>();
      if (projectNames.size() == 1 && !StringUtils.isEmpty(namesCsv))
        Collections.addAll(testNames, StringUtils.splitByWholeSeparator(namesCsv, ","));
      else {
        ISTWDotNETScripts dotNETScripts = projects.item(projectName).dotNETScripts();
        ISTWVisualTests visualTests = projects.item(projectName).visualTests();
        for (int i = 1; i <= dotNETScripts.count(); i++) {
          testNames.add(dotNETScripts.item(i).name());
        }
        for (int i = 1; i <= visualTests.count(); i++) {
          testNames.add(visualTests.item(i).name());
        }
      }

      for (String name : testNames) {
        String normalizedTestName = StringUtils.chomp(name);
        listener.getLogger().println(
            MessageFormat.format("[SilkTest Workbench] Execute test {0} in project {1}.", normalizedTestName,
                projectName));
        try {
          stWorkbench.execute(projectName, normalizedTestName, null, null, playbackenv);

          while (stWorkbench.playbackInProgress())
            Thread.sleep(WAIT_FOR_STWIDLE_TIMEOUT);

          ISTWExecutionResult lastResult = stWorkbench.lastResult();
          ISTWResult resultObject = lastResult.resultObject();
          writeResultFile(build.getWorkspace(), resultObject);
          result = result.combine(lastTestResult(lastResult)); // FIXME: after executed test
                                                               // lastResult.verificationsExecuted(); returns 0, do we
                                                               // need a delay?
          if (result.isWorseThan(Result.SUCCESS))
            writeErrorScreen(new FilePath(build.getRootDir()), resultObject);
        } catch (ComException e) {
          listener
              .error(
                  "[SilkTest Workbench] Execute test %s in project %s failed. Please verify if the configuration is correct.",
                  normalizedTestName, projectName);
          LOGGER.log(Level.SEVERE, "SilkTest Workbench execution failed.", e);
          result = result.combine(Result.FAILURE);
        } catch (IOException e) {
          listener.error("[SilkTest Workbench] Writting the result of test %s failed because of %s.",
              normalizedTestName, e.getLocalizedMessage());
          LOGGER.log(Level.SEVERE, "Writting result for SilkTest Workbench execution failed.", e);
          result = result.combine(Result.FAILURE);
        }
        LOGGER.fine("New result :" + result.toString());
      }
    }
    build.setResult(result);

    return result.isBetterThan(Result.FAILURE);
  }

  private void deleteResults(FilePath workspace, Calendar started) throws IOException, InterruptedException {
    FilePath resultDir = new FilePath(workspace, "SilkTestResults/Workbench");
    if (!resultDir.exists() || resultDir.lastModified() < started.getTimeInMillis())
      resultDir.deleteRecursive();
  }

  private Result lastTestResult(ISTWExecutionResult lastResult) {
    int passCriteria = lastResult.verificationPassCriteria();
    int executed = lastResult.verificationsExecuted();
    int passed = lastResult.verificationsPassed();
    if (!StringUtils.isEmpty(lastResult.playbackErrorString()))
      return Result.FAILURE;

    if (executed <= 0)
      return Result.SUCCESS; // no verifications in the test
    if (passCriteria <= (passed * 100 / executed))
      return Result.SUCCESS;
    else
      return Result.UNSTABLE;
  }

  private void writeResultFile(FilePath filePath, ISTWResult lastResult) throws IOException, InterruptedException {
    FilePath workbenchResults = createResultFolders(filePath);
    FilePath result = new FilePath(workbenchResults, lastResult.name() + "_" + lastResult.assetID() + ".xml");

    Holder<String> xmlFileName = new Holder<String>();
    lastResult.getXML(lastResult.currentVersion(), 1, xmlFileName, null);
    moveFileToDestination(result, xmlFileName.value);
  }

  private void writeErrorScreen(FilePath filePath, ISTWResult lastResult) throws IOException, InterruptedException {
    FilePath workbenchResults = createResultFolders(filePath);
    FilePath errorScreen = new FilePath(workbenchResults, lastResult.name() + "_" + lastResult.assetID() + ".png");
    Holder<String> errorScreenFileName = new Holder<String>();
    lastResult.getErrorScreen(lastResult.currentVersion(), errorScreenFileName);
    moveFileToDestination(errorScreen, errorScreenFileName.value);
  }

  private FilePath createResultFolders(FilePath filePath) throws IOException, InterruptedException {
    FilePath silktestResultRoot = new FilePath(filePath, "SilkTestResults");
    if (!silktestResultRoot.exists())
      silktestResultRoot.mkdirs();

    FilePath workbenchResults = new FilePath(silktestResultRoot, "Workbench");
    if (!workbenchResults.exists())
      workbenchResults.mkdirs();
    return workbenchResults;
  }

  private void moveFileToDestination(FilePath result, String fileName) throws FileNotFoundException, IOException,
      InterruptedException {
    InputStream is = null;
    try {
      File source = new File(fileName);
      is = new FileInputStream(source);
      result.copyFrom(is);
      source.delete();
    } finally {
      if (is != null)
        is.close();
    }
  }
}
