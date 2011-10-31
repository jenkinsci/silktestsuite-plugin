package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.DefaultValue;
import com4j.IID;
import com4j.Optional;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWProject extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "VisualTest"
   * </p>
   * @param bstrTestName Mandatory java.lang.String parameter.
   */

  @DISPID(6)
  @PropGet
  com4j.Com4jObject visualTest(
    java.lang.String bstrTestName);


  /**
   * <p>
   * Getter method for the COM property "DotNETScript"
   * </p>
   * @param bstrScriptName Mandatory java.lang.String parameter.
   */

  @DISPID(7)
  @PropGet
  com4j.Com4jObject dotNETScript(
    java.lang.String bstrScriptName);


  /**
   * @param bstrScriptName Mandatory java.lang.String parameter.
   * @param bAppendToResult Optional parameter. Default value is -1
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(8)
  boolean execute(
    java.lang.String bstrScriptName,
    @Optional @DefaultValue("-1") java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") java.lang.Object bIncrementCycle,
    @Optional java.lang.Object bstrPlaybackEnvironment);


  // Properties:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return The COM property Name as a java.lang.String
   */
  @DISPID(1)
  @PropGet
  java.lang.String getName();

  /**
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param newValue The new value for the COM property Name as a java.lang.String
   */
  @DISPID(1)
  @PropPut
  void setName(java.lang.String newValue);

  /**
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return The COM property Description as a java.lang.String
   */
  @DISPID(2)
  @PropGet
  java.lang.String getDescription();

  /**
   * <p>
   * Setter method for the COM property "Description"
   * </p>
   * @param newValue The new value for the COM property Description as a java.lang.String
   */
  @DISPID(2)
  @PropPut
  void setDescription(java.lang.String newValue);

  /**
   * <p>
   * Getter method for the COM property "DotNETScripts"
   * </p>
   * @return The COM property DotNETScripts as a com4j.Com4jObject
   */
  @DISPID(3)
  @PropGet
  com4j.Com4jObject getDotNETScripts();

  /**
   * <p>
   * Setter method for the COM property "DotNETScripts"
   * </p>
   * @param newValue The new value for the COM property DotNETScripts as a com4j.Com4jObject
   */
  @DISPID(3)
  @PropPut
  void setDotNETScripts(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "VisualTests"
   * </p>
   * @return The COM property VisualTests as a com4j.Com4jObject
   */
  @DISPID(4)
  @PropGet
  com4j.Com4jObject getVisualTests();

  /**
   * <p>
   * Setter method for the COM property "VisualTests"
   * </p>
   * @param newValue The new value for the COM property VisualTests as a com4j.Com4jObject
   */
  @DISPID(4)
  @PropPut
  void setVisualTests(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "Results"
   * </p>
   * @return The COM property Results as a com4j.Com4jObject
   */
  @DISPID(5)
  @PropGet
  com4j.Com4jObject getResults();

  /**
   * <p>
   * Setter method for the COM property "Results"
   * </p>
   * @param newValue The new value for the COM property Results as a com4j.Com4jObject
   */
  @DISPID(5)
  @PropPut
  void setResults(com4j.Com4jObject newValue);

}
