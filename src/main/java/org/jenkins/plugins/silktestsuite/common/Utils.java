package org.jenkins.plugins.silktestsuite.common;

import hudson.FilePath;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.common");

  public static boolean cleanupWorkspace(final FilePath workspace, final Calendar startTime)
      throws InterruptedException {
    try {
      final FilePath resultDir = new FilePath(workspace, "SilkTestResults");
      if (!resultDir.exists() || (resultDir.lastModified() < startTime.getTimeInMillis())) {
        resultDir.deleteRecursive();
      }
      return true;
    } catch (final IOException e) {
      LOGGER.log(Level.SEVERE, "Deleting the result folder failed.", e);
      return false;
    }
  }
}
