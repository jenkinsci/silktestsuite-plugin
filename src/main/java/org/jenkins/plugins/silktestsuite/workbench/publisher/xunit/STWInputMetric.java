package org.jenkins.plugins.silktestsuite.workbench.publisher.xunit;

import com.thalesgroup.dtkit.junit.model.JUnitModel;
import com.thalesgroup.dtkit.metrics.model.InputMetricXSL;
import com.thalesgroup.dtkit.metrics.model.InputType;
import com.thalesgroup.dtkit.metrics.model.OutputMetric;

public class STWInputMetric extends InputMetricXSL {
  private static final long serialVersionUID = 1L;

  @Override
  public String getXslName() {
    return "stwToJunit.xslt"; //$NON-NLS-1$
  }

  @Override
  public String getToolName() {
    return "SilkTest Workbench";
  }

  @Override
  public String getToolVersion() {
    return " > 2010";
  }

  @Override
  public InputType getToolType() {
    return InputType.TEST;
  }

  @Override
  public OutputMetric getOutputFormatType() {
    return JUnitModel.OUTPUT_JUNIT_4;
  }
}
