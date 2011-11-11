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
  public SilkTestWorkbenchBuilder(final String user, final String password, final String dsn, final String projectsCsv,
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
      throws InterruptedException {
    cleanupWorkspace(build, listener);

    final ISTWApp stWorkbench = login(build, listener);
    if (stWorkbench == null) {
      return false;
    }

    final List<String> projectNames = new ArrayList<String>();
    final ISTWProjects projects = stWorkbench.projects();
    if (StringUtils.isEmpty(projectsCsv)) {
      for (int i = 1; i <= projects.count(); i++) {
        projectNames.add(projects.item(i).name());
      }
    } else {
      Collections.addAll(projectNames, StringUtils.splitByWholeSeparator(projectsCsv, ","));
    }

    Result result = build.getResult() == null ? Result.SUCCESS : build.getResult();
    for (final String projectName : projectNames) {
      final List<String> testNames = new ArrayList<String>();
      listTestsForExecution(projectNames, projects, projectName, testNames);

      for (final String name : testNames) {
        final String normalizedTestName = StringUtils.chomp(name);
        listener.getLogger().println(
            MessageFormat.format("[SilkTest Workbench] Execute test {0} in project {1}.", normalizedTestName,
                projectName));
        try {
          executeTest(stWorkbench, projectName, normalizedTestName);
          result = writeResult(build, stWorkbench, result);
        } catch (final ComException e) {
          listener
              .error(
                  "[SilkTest Workbench] Execute test %s in project %s failed. Please verify if the configuration is correct.",
                  normalizedTestName, projectName);
          LOGGER.log(Level.SEVERE, "SilkTest Workbench execution failed.", e);
          result = result.combine(Result.FAILURE);
        } catch (final IOException e) {
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

  private Result writeResult(final AbstractBuild<?, ?> build, final ISTWApp stWorkbench, Result result)
      throws IOException, InterruptedException {
    final ISTWExecutionResult lastResult = stWorkbench.lastResult();
    final ISTWResult resultObject = lastResult.resultObject();
    writeResultFile(build.getWorkspace(), resultObject);
    result = result.combine(lastTestResult(lastResult)); // FIXME: after executed test
                                                         // lastResult.verificationsExecuted(); returns 0, do we
                                                         // need a delay?
    if (result.isWorseThan(Result.SUCCESS)) {
      writeErrorScreen(new FilePath(build.getRootDir()), resultObject);
    }
    return result;
  }

  private void executeTest(final ISTWApp stWorkbench, final String projectName, final String normalizedTestName)
      throws InterruptedException {
    stWorkbench.execute(projectName, normalizedTestName, null, null, playbackenv);

    while (stWorkbench.playbackInProgress()) {
      Thread.sleep(WAIT_FOR_STWIDLE_TIMEOUT);
    }
  }

  private void listTestsForExecution(final List<String> projectNames, final ISTWProjects projects,
      final String projectName, final List<String> testNames) {
    if ((projectNames.size() == 1) && !StringUtils.isEmpty(namesCsv)) {
      Collections.addAll(testNames, StringUtils.splitByWholeSeparator(namesCsv, ","));
    } else {
      final ISTWDotNETScripts dotNETScripts = projects.item(projectName).dotNETScripts();
      final ISTWVisualTests visualTests = projects.item(projectName).visualTests();
      for (int i = 1; i <= dotNETScripts.count(); i++) {
        testNames.add(dotNETScripts.item(i).name());
      }
      for (int i = 1; i <= visualTests.count(); i++) {
        testNames.add(visualTests.item(i).name());
      }
    }
  }

  private ISTWApp login(final AbstractBuild<?, ?> build, final BuildListener listener) {
    ISTWApp stWorkbench = null;
    try {
      stWorkbench = ClassFactory.createSTWApp();
      if (!stWorkbench.login(user, password, dsn, Boolean.TRUE, Boolean.FALSE)) {
        listener.error("[SilkTest Workbench] Log-in at SilkTest Workbench failed.");
        LOGGER.warning("log in to Silktest Workbench failed.");
        build.setResult(Result.FAILURE);
        stWorkbench = null;
      }
    } catch (final Exception e) {
      listener
          .error("[SilkTest Workbench] Cannot find SilkTest Workbench. Verify if the installation is completed successfully.");
      LOGGER.log(Level.SEVERE, "SilkTEst Workbench is not installed.", e);
      build.setResult(Result.FAILURE);
    }
    return stWorkbench;
  }

  private void cleanupWorkspace(final AbstractBuild<?, ?> build, final BuildListener listener)
      throws InterruptedException {
    try {
      final FilePath resultDir = new FilePath(build.getWorkspace(), "SilkTestResults/Workbench");
      if (!resultDir.exists() || (resultDir.lastModified() < build.getTimestamp().getTimeInMillis())) {
        resultDir.deleteRecursive();
      }
    } catch (final IOException e) {
      listener.error("[SilkTest Workbench] Deleting the result folder failed because of %s.", e.getLocalizedMessage());
      LOGGER.log(Level.SEVERE, "Deleting the result folder failed.", e);
      build.setResult(Result.FAILURE);
    }
  }

  private Result lastTestResult(final ISTWExecutionResult lastResult) {
    final int passCriteria = lastResult.verificationPassCriteria();
    final int executed = lastResult.verificationsExecuted();
    final int passed = lastResult.verificationsPassed();
    if (!StringUtils.isEmpty(lastResult.playbackErrorString())) {
      return Result.FAILURE;
    }

    if (executed <= 0) {
      return Result.SUCCESS; // no verifications in the test
    }
    if (passCriteria <= ((passed * 100) / executed)) {
      return Result.SUCCESS;
    } else {
      return Result.UNSTABLE;
    }
  }

  private void writeResultFile(final FilePath filePath, final ISTWResult lastResult) throws IOException,
      InterruptedException {
    final FilePath workbenchResults = createResultFolders(filePath);
    final FilePath result = new FilePath(workbenchResults, lastResult.name() + "_" + lastResult.assetID() + ".xml");

    final Holder<String> xmlFileName = new Holder<String>();
    lastResult.getXML(lastResult.currentVersion(), 1, xmlFileName, null);
    moveFileToDestination(result, xmlFileName.value);
  }

  private void writeErrorScreen(final FilePath filePath, final ISTWResult lastResult) throws IOException,
      InterruptedException {
    final FilePath workbenchResults = createResultFolders(filePath);
    final FilePath errorScreen = new FilePath(workbenchResults, lastResult.name() + "_" + lastResult.assetID() + ".png");
    final Holder<String> errorScreenFileName = new Holder<String>();
    lastResult.getErrorScreen(lastResult.currentVersion(), errorScreenFileName);
    moveFileToDestination(errorScreen, errorScreenFileName.value);
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

  private void moveFileToDestination(final FilePath result, final String fileName) throws FileNotFoundException,
      IOException, InterruptedException {
    InputStream is = null;
    try {
      final File source = new File(fileName);
      is = new FileInputStream(source);
      result.copyFrom(is);
      source.delete();
    } finally {
      if (is != null) {
        is.close();
      }
    }
  }
}
