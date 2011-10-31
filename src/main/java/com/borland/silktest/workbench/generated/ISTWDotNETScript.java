package com.borland.silktest.workbench.generated  ;

import com4j.DISPID;
import com4j.DefaultValue;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench .NET Script Interface
 */
@IID("{26D4D3B5-0D0E-4EC0-933A-8E54559DD2C1}")
public interface ISTWDotNETScript extends com.borland.silktest.workbench.generated._ISTWAsset {
  // Methods:
  /**
   * <p>
   * Method Open
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  boolean open();


  /**
   * <p>
   * Method Execute
   * </p>
   * @param bAppendToResult Optional parameter. Default value is -1
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  boolean execute(
    @Optional @DefaultValue("-1") @MarshalAs(NativeType.VARIANT) java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") @MarshalAs(NativeType.VARIANT) java.lang.Object bIncrementCycle,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrPlaybackEnvironment);


  /**
   * <p>
   * Method OpenToLine
   * </p>
   * @param nLineNumber Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  int openToLine(
    int nLineNumber);


  // Properties:
}
