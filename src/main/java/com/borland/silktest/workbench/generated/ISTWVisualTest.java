package com.borland.silktest.workbench.generated  ;

import com4j.DISPID;
import com4j.DefaultValue;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench Visual test Interface
 */
@IID("{31D07ACC-EC54-4ED5-A8CC-D52949DE6193}")
public interface ISTWVisualTest extends com.borland.silktest.workbench.generated._ISTWAsset {
  // Methods:
  /**
   * <p>
   * Property ParameterCount
   * </p>
   * <p>
   * Getter method for the COM property "ParameterCount"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  int parameterCount();


  /**
   * <p>
   * Method Open
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  boolean open();


  /**
   * <p>
   * method Execute
   * </p>
   * @param bAppendToResult Optional parameter. Default value is -1
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  boolean execute(
    @Optional @DefaultValue("-1") @MarshalAs(NativeType.VARIANT) java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") @MarshalAs(NativeType.VARIANT) java.lang.Object bIncrementCycle,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrPlaybackEnvironment);


  /**
   * <p>
   * Property ParameterItem
   * </p>
   * <p>
   * Getter method for the COM property "ParameterItem"
   * </p>
   * @param pvIndex Mandatory java.lang.Object parameter.
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWParameter
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(19)
  com.borland.silktest.workbench.generated.ISTWParameter parameterItem(
    java.lang.Object pvIndex);


  /**
   * <p>
   * Method OpenToItem
   * </p>
   * @param nItemNumber Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(20)
  int openToItem(
    int nItemNumber);


  // Properties:
}
