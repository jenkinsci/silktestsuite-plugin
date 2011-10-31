package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.DefaultValue;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench Project Interface
 */
@IID("{39F5A26A-5FC7-44D1-9883-B80FB947ECB6}")
public interface ISTWProject extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Property Name
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.String name();


  /**
   * <p>
   * Property Description
   * </p>
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String description();


  /**
   * <p>
   * Property .NET Scripts
   * </p>
   * <p>
   * Getter method for the COM property "DotNETScripts"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWDotNETScripts
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  com.borland.silktest.workbench.generated.ISTWDotNETScripts dotNETScripts();


  /**
   * <p>
   * Method Execute
   * </p>
   * @param bstrDotNETScriptName Mandatory java.lang.String parameter.
   * @param bAppendToResult Optional parameter. Default value is -1
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  boolean execute(
    java.lang.String bstrDotNETScriptName,
    @Optional @DefaultValue("-1") @MarshalAs(NativeType.VARIANT) java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") @MarshalAs(NativeType.VARIANT) java.lang.Object bIncrementCycle,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrPlaybackEnvironment);


  /**
   * <p>
   * Property Visual tests
   * </p>
   * <p>
   * Getter method for the COM property "VisualTests"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWVisualTests
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  com.borland.silktest.workbench.generated.ISTWVisualTests visualTests();


  /**
   * <p>
   * Property Results
   * </p>
   * <p>
   * Getter method for the COM property "Results"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWResults
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  com.borland.silktest.workbench.generated.ISTWResults results();


  /**
   * <p>
   * Property .NET Script
   * </p>
   * <p>
   * Getter method for the COM property "DotNETScript"
   * </p>
   * @param bstrScriptName Mandatory java.lang.String parameter.
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWDotNETScript
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  com.borland.silktest.workbench.generated.ISTWDotNETScript dotNETScript(
    java.lang.String bstrScriptName);


  /**
   * <p>
   * Property Visual test
   * </p>
   * <p>
   * Getter method for the COM property "VisualTest"
   * </p>
   * @param bstrTestName Mandatory java.lang.String parameter.
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWVisualTest
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  com.borland.silktest.workbench.generated.ISTWVisualTest visualTest(
    java.lang.String bstrTestName);


  // Properties:
}
