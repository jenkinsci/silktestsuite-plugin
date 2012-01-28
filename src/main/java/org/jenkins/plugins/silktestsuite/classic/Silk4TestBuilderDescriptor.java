package org.jenkins.plugins.silktestsuite.classic;

import hudson.Extension;
import hudson.matrix.MatrixProject;
import hudson.model.AbstractProject;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

@Extension
public final class Silk4TestBuilderDescriptor extends BuildStepDescriptor<Builder> {
  public Silk4TestBuilderDescriptor() {
    super(Silk4TestBuilder.class);
    load();
  }

  @Override
  public String getDisplayName() {
    return "SilkTest 4Test Configuration";
  }

  @Override
  public boolean isApplicable(@SuppressWarnings("rawtypes") Class<? extends AbstractProject> jobType) {
    return FreeStyleProject.class.equals(jobType) || MatrixProject.class.equals(jobType);
  }
}
