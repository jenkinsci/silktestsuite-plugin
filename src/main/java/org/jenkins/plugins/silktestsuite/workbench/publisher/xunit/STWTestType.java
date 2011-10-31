package org.jenkins.plugins.silktestsuite.workbench.publisher.xunit;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

import com.thalesgroup.dtkit.metrics.hudson.api.descriptor.TestTypeDescriptor;
import com.thalesgroup.dtkit.metrics.hudson.api.type.TestType;

public class STWTestType extends TestType {
  private static final long serialVersionUID = 1L;
  
  @DataBoundConstructor
  public STWTestType(String pattern, boolean failureIfNotNew,
      boolean deleteOutputFiles, boolean stopProcessingIfError) {
    super(pattern, failureIfNotNew, deleteOutputFiles, stopProcessingIfError);
  }

  @Extension
  public static class DescriptorImpl extends TestTypeDescriptor<STWTestType> {

    public DescriptorImpl() {
      super(STWTestType.class, STWInputMetric.class);
    }

    @Override
    public String getId() {
      return STWTestType.class.getCanonicalName();
    }
  }
}
