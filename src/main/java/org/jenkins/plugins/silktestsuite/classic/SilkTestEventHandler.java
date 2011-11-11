package org.jenkins.plugins.silktestsuite.classic;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.logging.Level;

public final class SilkTestEventHandler { // implements ISilkTestEventHandler {

  private boolean testRunFinished;
  private boolean failed;
  private final PrintStream logger;

  public SilkTestEventHandler(final PrintStream logger) {
    this.logger = logger;
  }

  // @Override
  public void indicateDisconnection() {
  }

  // @Override
  public void indicateInfo(final int severity, final String msg) {
    final Level level = getLogLevel(severity);
    logger.println(MessageFormat.format("{0}: {1}", level, msg));
  }

  private Level getLogLevel(final int severity) {
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

  // @Override
  public void indicateTestcaseEnd() {
    testRunFinished = true;
  }

  // @Override
  public void indicateTimer(final String timer, final long value) {
  }

  // @Override
  public boolean raiseErrorArg(final int iError, final String sError, final int iArg, final int iSeverity) {
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
