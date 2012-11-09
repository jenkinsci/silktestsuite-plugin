package org.jenkins.plugins.silktestsuite.autotoolinstaller;

import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import hudson.model.Hudson;
import hudson.model.Node;
import hudson.tools.ToolInstaller;
import hudson.tools.ToolInstallerDescriptor;
import hudson.tools.ToolInstallation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;

public class SilkTestAutoInstaller extends ToolInstaller {

  @DataBoundConstructor
  public SilkTestAutoInstaller(String id) {
    super(id);
  }

  @Override
  public FilePath performInstallation(ToolInstallation tool, Node node, TaskListener log) throws IOException, InterruptedException {
    if (!node.toComputer().getEnvironment().get("os.name").contains("Windows")) {
      log.error("Cannot install SilkTest Classic Driver on a non Windows node, because SilkTest is only running on Windows nodes.");
      throw new IOException("Trying to install SilkTest on a non Windows node.");
    }
    
    FilePath classicTool = preferredLocation(tool, node);
    if (classicTool.exists())
      classicTool.mkdirs();
    
    File silkTestDriverOnMaster = findSourceOnMaster();
    FilePath target = new FilePath(classicTool, "silktestclassic.jar");
    new FilePath(silkTestDriverOnMaster).copyTo(target);
    
    return target;
  }

  private File findSourceOnMaster() {
    File pluginDirOnMaster = new File(Hudson.getInstance().getRootDir(), "plugin/silktestsuite");
    File[] listFiles = pluginDirOnMaster.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.startsWith("silktestclassic") && name.endsWith(".jar");
      }
    });
    String root = new File(".").getAbsolutePath();    
    return listFiles != null ? listFiles[0] : new File(root.substring(0, root.lastIndexOf('.')),
        "src/main/webapp/silktestclassic-0.0.1-SNAPSHOT-jar-with-dependencies.jar"); // FIXME:
    // remove absolute path referencing the classic driver in case of debug mode
  }

  @Extension
  public static final class DesciptorImpl extends ToolInstallerDescriptor<SilkTestAutoInstaller> {
    @Override
    public String getDisplayName() {
      return "Build-in Distribution";
    }

    @Override
    public boolean isApplicable(Class<? extends ToolInstallation> toolType) {
      return toolType == SilkTestInstallation.class;
    }
  }

}
