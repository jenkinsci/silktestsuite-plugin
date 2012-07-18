package org.jenkins.plugins.silktestsuite.workbench;

import hudson.Extension;
import hudson.matrix.MatrixProject;
import hudson.model.AbstractProject;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;

import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

@Extension
public class SilkTestWorkbenchDescriptor extends BuildStepDescriptor<Builder> {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger("org.jenkins.plugins.silktestsuite.visualtest");

  public SilkTestWorkbenchDescriptor() {
    super(SilkTestWorkbenchCOMBuilder.class);
    load();
  }

  @Override
  public String getDisplayName() {
    return "SilkTest Workbench Configuration";
  }

  @Override
  public boolean isApplicable(@SuppressWarnings("rawtypes") Class<? extends AbstractProject> jobType) {
    return FreeStyleProject.class.equals(jobType) || MatrixProject.class.equals(jobType);
  }

  @Override
  public Builder newInstance(StaplerRequest req, JSONObject formData) throws hudson.model.Descriptor.FormException {

    return super.newInstance(req, formData);
  }

  public FormValidation doCheckUser(StaplerRequest rep, StaplerResponse rsp, @QueryParameter("value") final String value) {
    return FormValidation.validateRequired(value);
  }

  public FormValidation doCheckPassword(StaplerRequest rep, StaplerResponse rsp,
      @QueryParameter("value") final String value) {
    return FormValidation.validateRequired(value);
  }

  public FormValidation doCheckDsn(StaplerRequest rep, StaplerResponse rsp, @QueryParameter("value") final String value) {
    return FormValidation.validateRequired(value);
  }

  public FormValidation doCheckProjectsCsv(StaplerRequest rep, StaplerResponse rsp,
      @QueryParameter("projectsCsv") final String projects) {
    if (StringUtils.isEmpty(projects))
      return FormValidation.ok();
    else
      return projects.matches("[\\p{Alnum}-_,.(\\x20)]*") ? FormValidation.ok() : FormValidation
          .error("Invalid characters found! Enter a list of project names separated by a comma.");
  }

  public FormValidation doCheckNamesCsv(StaplerRequest rep, StaplerResponse rsp,
      @QueryParameter("namesCsv") final String testNames, @QueryParameter("projectsCsv") final String projects) {
    if (StringUtils.isEmpty(testNames))
      return FormValidation.ok();
    else if (StringUtils.isEmpty(projects))
      return FormValidation.error("You need to specify also a project name if you enter test names.");
    else
      return testNames.matches("[\\p{Alnum}-_,.(\\x20)]*") ? FormValidation.ok() : FormValidation
          .error("Invalid characters found! Enter a list of test names separated by a comma.");
  }
}
