package org.jenkins.plugins.silktestsuite.workbench;

import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.remoting.Callable;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.borland.silktest.workbench.generated.ClassFactory;
import com.borland.silktest.workbench.generated.ISTWApp;
import com.borland.silktest.workbench.generated.ISTWDotNETScripts;
import com.borland.silktest.workbench.generated.ISTWExecutionResult;
import com.borland.silktest.workbench.generated.ISTWProjects;
import com.borland.silktest.workbench.generated.ISTWResult;
import com.borland.silktest.workbench.generated.ISTWVisualTests;
import com4j.ComException;
import com4j.Holder;

public class STWCOMExecution implements Callable<Result, RuntimeException> {
  private static final int WAIT_FOR_STWIDLE_TIMEOUT = 1000;
  
  private static final long serialVersionUID = 1L;
  private String user;
  private String password;
  private String dsn;
  private String projectsCsv;
  private String namesCsv;
  private String playbackenv;
  private String workspace;

  private BuildListener listener;

  public STWCOMExecution(String user, String password, String dsn, String projectsCsv, String namesCsv, String playbackenv, String workspace, BuildListener listener) {
    this.user = user;
    this.password = password;
    this.dsn = dsn;
    this.projectsCsv = projectsCsv;
    this.namesCsv = namesCsv;
    this.playbackenv = playbackenv;
    this.workspace = workspace;
    this.listener = listener;
  }


  @Override
  public Result call() throws RuntimeException {
    final ISTWApp stWorkbench = login(listener);
    if (stWorkbench == null) {
      return Result.FAILURE;
    }
    
    final List<String> projectNames = new ArrayList<String>();
    final ISTWProjects projects = stWorkbench.projects();
    if (StringUtils.isEmpty(projectsCsv)) {
      for (int i = 1; i <= projects.count(); i++) {
        projectNames.add(projects.item(i).name());
      }
    } else
      Collections.addAll(projectNames, StringUtils.splitByWholeSeparator(projectsCsv, ","));
    
    Result result = Result.NOT_BUILT;
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
          result = result.combine(writeResult(new File(workspace), stWorkbench));
        } catch (final ComException e) {
          throw new RuntimeException(String.format("[SilkTest Workbench] Execute test %s in project %s failed. Please verify if the configuration is correct.",normalizedTestName, projectName), e);
        } catch (final IOException e) {
          throw new RuntimeException(String.format("[SilkTest Workbench] Writting the result of test %s failed because of %s.",
              normalizedTestName, e.getLocalizedMessage()), e);
        }
      }
    }
    
    return result;
  }      

  
  private ISTWApp login(final BuildListener listener) {
    ISTWApp stWorkbench = null;
    try {
      stWorkbench = ClassFactory.createSTWApp();
      if (!stWorkbench.login(user, password, dsn, Boolean.TRUE, Boolean.FALSE)) {
        listener.error("[SilkTest Workbench] Log-in at SilkTest Workbench failed.");
        stWorkbench = null;
      }
    } catch (final Exception e) {
      listener
          .error("[SilkTest Workbench] Cannot find SilkTest Workbench. Verify if the installation is completed successfully.");
    }
    return stWorkbench;
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

  private void executeTest(final ISTWApp stWorkbench, final String projectName, final String normalizedTestName) {
    stWorkbench.execute(projectName, normalizedTestName, null, null, playbackenv);
  
    try {
      while (stWorkbench.playbackInProgress()) {
          Thread.sleep(WAIT_FOR_STWIDLE_TIMEOUT);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private Result writeResult(final File workspace, final ISTWApp stWorkbench)
      throws IOException {
    final ISTWExecutionResult lastResult = stWorkbench.lastResult();
    final ISTWResult resultObject = lastResult.resultObject();
    writeResultFile(workspace, resultObject);
    Result result = lastTestResult(lastResult);
    
    if (result.isWorseThan(Result.SUCCESS)) {
      writeErrorScreen(workspace, resultObject);
    }
    return result;
  }

  private void writeResultFile(final File workspace, final ISTWResult lastResult) throws IOException {
    final File workbenchResults = createResultFolders(workspace);
    final File result = new File(workbenchResults, lastResult.name() + "_" + lastResult.assetID() + ".xml");
  
    final Holder<String> xmlFileName = new Holder<String>();
    lastResult.getXML(lastResult.currentVersion(), 1, xmlFileName, null);
    new File(xmlFileName.value).renameTo(result);
  }

  private void writeErrorScreen(final File workspace, final ISTWResult lastResult) throws IOException {
    final File workbenchResults = createResultFolders(workspace);
    final File errorScreen = new File(workbenchResults, lastResult.name() + "_" + lastResult.assetID() + ".png");
    final Holder<String> errorScreenFileName = new Holder<String>();
    lastResult.getErrorScreen(lastResult.currentVersion(), errorScreenFileName);
    new File(errorScreenFileName.value).renameTo(errorScreen);
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

  private File createResultFolders(final File workspace) throws IOException {
    final File silktestResultRoot = new File(workspace, "SilkTestResults");
    if (!silktestResultRoot.exists()) {
      silktestResultRoot.mkdirs();
    }

    final File workbenchResults = new File(silktestResultRoot, "Workbench");
    if (!workbenchResults.exists()) {
      workbenchResults.mkdirs();
    }
    return workbenchResults;
  }
}
