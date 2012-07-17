package org.jenkins.plugins.silktestsuite.common;

import hudson.remoting.Callable;

import java.util.Map;

final public class RemoteEnvironmentVariables implements Callable<Map<String, String>, RuntimeException> {
  private static final long serialVersionUID = 1L;

  @Override
  public Map<String, String> call() throws RuntimeException {
    return System.getenv();
  }
}
