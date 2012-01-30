package org.jenkins.plugins.silktestsuite.autotoolinstaller;

import hudson.tools.DownloadFromUrlInstaller;
import hudson.tools.ToolInstallation;

import org.kohsuke.stapler.DataBoundConstructor;

public class SilkTestAutoInstaller extends DownloadFromUrlInstaller {

  @DataBoundConstructor
  public SilkTestAutoInstaller(String id) {
    super(id);
  }

  // @Extension
  public static final class DesciptorImpl extends DownloadFromUrlInstaller.DescriptorImpl<SilkTestAutoInstaller> {
    @Override
    public String getDisplayName() {
      return "Local Download";
    }

    @Override
    public boolean isApplicable(Class<? extends ToolInstallation> toolType) {
      return toolType == SilkTestInstallation.class;
    }
  }

}
