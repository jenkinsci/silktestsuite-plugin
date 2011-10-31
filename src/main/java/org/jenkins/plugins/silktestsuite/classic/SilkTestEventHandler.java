package org.jenkins.plugins.silktestsuite.classic;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.logging.Level;

import com.segue.silktest.controller.ISilkTestEventHandler;

public final class SilkTestEventHandler implements ISilkTestEventHandler {

  private boolean testRunFinished;
  private boolean failed;
  private final PrintStream logger;

  public SilkTestEventHandler(PrintStream logger) {
    this.logger = logger;
  }

  @Override
  public void indicateDisconnection() {
  }

  @Override
  public void indicateInfo(int severity, String msg) {
    Level level = getLogLevel(severity);
    logger.println(MessageFormat.format("{0}: {1}", level, msg));
  }

  private Level getLogLevel(int severity) {
    Level level;
    switch (severity) {
    case 1:
      level = Level.INFO;
      break;
    case 2:
      level = Level.WARNING;
      break;
    default:
      level = Level.SEVERE;
    }
    return level;
  }

  @Override
  public void indicateTestcaseEnd() {
    testRunFinished = true;
  }

  @Override
  public void indicateTimer(String timer, long value) {
  }

  @Override
  public boolean raiseErrorArg(int iError, String sError, int iArg, int iSeverity) {
    this.logger.println(MessageFormat.format("{0}: {1} - {2} ({3})", getLogLevel(iSeverity), iError, sError, iArg));
    failed = true;
    return true;
  }

  public boolean isTestRunFinished() {
    return testRunFinished;
  }

  public boolean isFailed() {
    return failed;
  }
}
