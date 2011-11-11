package org.jenkins.plugins.silktestsuite.classic;

import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.model.Hudson;
import hudson.tasks.Builder;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;

public final class Silk4TestBuilder extends Builder {
  private static final String SEGUE_HOME = System.getenv("SEGUE_HOME");
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.classic");
  private final String optionFile;
  private final String testScript;
  private final String query;

  static {
    System.load(SEGUE_HOME + "/stCtrlBridge.dll");
  }

  @DataBoundConstructor
  public Silk4TestBuilder(final String testScript, final String optionFile, final String query) {
    super();
    this.testScript = testScript;
    this.optionFile = optionFile;
    this.query = query;
  }

  public String getOptionFile() {
    return optionFile;
  }

  public String getTestScript() {
    return testScript;
  }

  @Override
  public boolean perform(final AbstractBuild<?, ?> build, final Launcher launcher, final BuildListener listener)
      throws InterruptedException, IOException {
    // String pathToPartner = SEGUE_HOME + "/partner.exe";
    // String resultPath = "SilkTest/Classic";
    // new FilePath(new File(resultPath)).mkdirs();
    //
    // if (!new FilePath(new File(pathToPartner)).exists()) {
    // return handleSilkTestNotFound(build, listener, pathToPartner);
    // }
    //
    // SilkTestEventHandler eventHandler = new SilkTestEventHandler(listener.getLogger());
    // SilkTestController stCtrl = SilkTestController.createSilkTestController(eventHandler);
    // try {
    // if (!stCtrl.start(pathToPartner, 10000)) {
    // return handleSilkTestNotFound(build, listener, pathToPartner);
    // }
    // setOptionIfNotNull(stCtrl, "OptionSet", this.optionFile);
    // setOptionIfNotNull(stCtrl, "ReportCompileErrors", "TRUE");
    // setOptionIfNotNull(stCtrl, "ResFilename", resultPath);
    //
    // if (this.testScript.endsWith(".t"))
    // stCtrl.execute(this.testScript, null, query);
    // else
    // stCtrl.execute(this.testScript, null, query, SilkTestController.EXEC_MODE_PLAN);
    //
    // synchronized (stCtrl) {
    // if (!eventHandler.isTestRunFinished())
    // stCtrl.wait();
    // }
    // } catch (InterruptedException e) {
    // listener.fatalError(MessageFormat.format("Cannot execute testscript {0}.", this.testScript));
    // LOGGER.log(Level.SEVERE, "Cannot execute SilkTest Classic testscript.", e);
    // build.setResult(Result.FAILURE);
    // return false;
    // } finally {
    // if (stCtrl != null) {
    // stCtrl.checkAgent();
    // stCtrl.cleanup();
    // stCtrl.exit();
    // stCtrl.destroy();
    // }
    // }
    //
    // build.setResult(eventHandler.isFailed() ? Result.UNSTABLE : Result.SUCCESS);
    return true;
  }

  private boolean handleSilkTestNotFound(final AbstractBuild<?, ?> build, final BuildListener listener,
      final String pathToPartner) {
    listener.fatalError(MessageFormat.format("Cannot start SilkTest with {0}.", pathToPartner));
    build.setResult(Result.FAILURE);
    return false;
  }

  // private void setOptionIfNotNull(final SilkTestController stCtrl, final String option, final String value) {
  // if ((value != null) && !"".equals(value)) {
  // stCtrl.setOption(option, value);
  // }
  // }

  @Override
  public Silk4TestBuilderDescriptor getDescriptor() {
    return (Silk4TestBuilderDescriptor) Hudson.getInstance().getDescriptor(getClass());
  }
}
