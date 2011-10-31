package org.jenkins.plugins.silktestsuite.workbench.publisher.xunit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestSTWXunitTestResultConvertion extends AbstractTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  @Ignore
  public void testPassedResultTransformation() throws Exception {
    convertAndValidate("result_passed.xml", "stwToJunit.xslt", "expectedresult_passed.xml");
  }
}
