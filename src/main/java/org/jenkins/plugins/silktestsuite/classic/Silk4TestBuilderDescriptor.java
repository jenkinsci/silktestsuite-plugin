package org.jenkins.plugins.silktestsuite.classic;

import hudson.Extension;
import hudson.FilePath;
import hudson.matrix.MatrixProject;
import hudson.model.TopLevelItem;
import hudson.model.AbstractProject;
import hudson.model.FreeStyleProject;
import hudson.model.Hudson;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.stapler.QueryParameter;

import com.google.common.base.Strings;

@Extension
public final class Silk4TestBuilderDescriptor extends BuildStepDescriptor<Builder> {
  private static final List<String> PROJECT_POSTFIXES = Arrays.asList(".vtp", ".vts", ".stp");
  private static final List<String> TESTSCRIPT_POSTFIXES = Arrays.asList(".t", ".g.t", ".pln");

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

  public FormValidation doCheckTestScript(@QueryParameter String value) {
    if (Strings.isNullOrEmpty(value))
      return FormValidation.validateRequired(value);
    if (isTestSciptFile(value))
      return validateFileExistInWorkspace(value);
    else
      return FormValidation.error(MessageFormat.format("Not a valid test script file. Valid postfixes are {0}",
          TESTSCRIPT_POSTFIXES.toString()));
  }

  public FormValidation doCheckOptionFile(@QueryParameter String value) {
    if (Strings.isNullOrEmpty(value) || value.endsWith(".opt"))
      return validateFileExistInWorkspace(value);
    else
      return FormValidation.error("Not a valid option file. Valid postfixe is [*.opt]");
  }

  public FormValidation doCheckConfigFile(@QueryParameter String value) {
    if (Strings.isNullOrEmpty(value) || isIniFile(value) || isProjectFile(value))
      return validateFileExistInWorkspace(value);
    else
      return FormValidation.error(MessageFormat.format(
          "not a valid configuration file. Valid postfixes are [*.ini] or {0}.", PROJECT_POSTFIXES.toString()));
  }

  public FormValidation doCheckTimeout(@QueryParameter String value) {
    return FormValidation.validatePositiveInteger(value);
  }

  static boolean isIniFile(String configFile) {
    if (Strings.isNullOrEmpty(configFile))
      return false;
    return configFile.endsWith(".ini");
  }

  static boolean isProjectFile(String projectFile) {
    if (Strings.isNullOrEmpty(projectFile))
      return false;
    return fitsFileFormat(PROJECT_POSTFIXES, projectFile);
  }

  static boolean isTestSciptFile(String testScriptFile) {
    return fitsFileFormat(TESTSCRIPT_POSTFIXES, testScriptFile);
  }

  private static boolean fitsFileFormat(List<String> postfixes, String fileName) {
    for (String postfix : postfixes) {
      if (fileName.endsWith(postfix))
        return true;
    }
    return false;
  }

  private FormValidation validateFileExistInWorkspace(String workspaceRelativePath) {
    if (!Strings.isNullOrEmpty(workspaceRelativePath)) {
      TopLevelItem item = Hudson.getInstance().getItem("classic");
      FilePath filePath = new FilePath(Hudson.getInstance().getWorkspaceFor(item), workspaceRelativePath);
      try {
        if (filePath.exists())
          return FormValidation.ok();
        else
          return FormValidation.warning(MessageFormat.format("File [{0}] does not exist in workspace.",
              workspaceRelativePath));
      } catch (Exception e) {
        return FormValidation.error(e.getLocalizedMessage());
      }
    } else
      return FormValidation.ok();
  }
}
