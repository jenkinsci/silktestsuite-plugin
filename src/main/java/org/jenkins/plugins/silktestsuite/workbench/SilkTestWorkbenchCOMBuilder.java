package org.jenkins.plugins.silktestsuite.workbench;

import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.tasks.Builder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.jenkins.plugins.silktestsuite.common.Utils;
import org.kohsuke.stapler.DataBoundConstructor;

public class SilkTestWorkbenchCOMBuilder extends Builder {
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.workbench");

  private final String user;
  private final String password;
  private final String dsn;
  private final String projectsCsv;
  private final String namesCsv;
  private final String playbackenv;

  @DataBoundConstructor
  public SilkTestWorkbenchCOMBuilder(final String user, final String password, final String dsn, final String projectsCsv,
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
    
//    boolean windowsOS = launcher.getChannel().call(new Callable<Boolean, RuntimeException>() {
//      private static final long serialVersionUID = 1L;
//
//      @Override
//      public Boolean call() throws RuntimeException {
//        return true;
//      }
//    });
//    
//    if (!windowsOS) {
//      listener.error("Only available on Windows systems.");
//      LOGGER.severe("This operation system is not supported with SilkTest.");
//      build.setResult(Result.ABORTED);
//      return true;
//    }
    
    final String workspace = build.getWorkspace().getRemote();
    if (!Utils.cleanupWorkspace(launcher, workspace, build.getTimestamp())) {
      listener.error("[SilkTest Workbench] Deleting the result folder failed.");
      build.setResult(Result.FAILURE);
      return false;
    }
    
    Result result;
    try {
      result = launcher.getChannel().call(new STWCOMExecution(user, password, dsn, projectsCsv, namesCsv, playbackenv, workspace, listener));
    } catch (RuntimeException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      result = Result.FAILURE;
    }

    result = build.getResult() == null ? result : build.getResult().combine(result);
    build.setResult(result);

    return result.isBetterThan(Result.FAILURE);
  }

 
}
