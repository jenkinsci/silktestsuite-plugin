package org.jenkins.plugins.silktestsuite.autotoolinstaller;

import hudson.tools.ToolDescriptor;
import hudson.tools.ToolInstaller;
import hudson.tools.ToolProperty;
import hudson.tools.ToolInstallation;

import java.util.Collections;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

public class SilkTestInstallation extends ToolInstallation {
  private static final long serialVersionUID = 1L;

  @DataBoundConstructor
  public SilkTestInstallation(String name, String home, List<? extends ToolProperty<?>> properties) {
    super(name, home, properties);
  }

  // @Extension
  public static final class DescriptorImpl extends ToolDescriptor<SilkTestInstallation> {

    @Override
    public String getDisplayName() {
      return "Borland SilkTest";
    }

    @Override
    public List<? extends ToolInstaller> getDefaultInstallers() {
      return Collections.singletonList(new SilkTestAutoInstaller(null));
    }

  }
}
