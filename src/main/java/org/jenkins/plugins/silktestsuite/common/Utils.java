package org.jenkins.plugins.silktestsuite.common;

import hudson.Launcher;
import hudson.remoting.Callable;

import java.io.File;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.common");

  public static boolean cleanupWorkspace(final Launcher launcher, final String workspace, final Calendar startTime) {
    try {
      return launcher.getChannel().call(new Callable<Boolean, Throwable>() {
        private static final long serialVersionUID = 1L;

        @Override
        public Boolean call() {
          final File resultDir = new File(workspace, "SilkTestResults");
          deleteRecursive(resultDir);
          return true;
        }

        private void deleteRecursive(File file) {
          if (file != null) {
            if (file.isFile() && (file.lastModified() < startTime.getTimeInMillis()))
              file.delete();
            else {
              for (File f : file.listFiles()) {
                deleteRecursive(f);
              }
            }
          }
        }
      });
    } catch (Throwable e) {
      LOGGER.log(Level.SEVERE, "Deleting the result folder failed.", e);
      return false;
    }
  }
}
