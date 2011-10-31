package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench .NET Scripts Collection Interface
 */
@IID("{554FE5AF-91CF-483C-931B-D09A6D2D3266}")
public interface ISTWDotNETScripts extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Property Count
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  int count();


  /**
   * <p>
   * Property Item
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param vIndex Mandatory java.lang.Object parameter.
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWDotNETScript
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  com.borland.silktest.workbench.generated.ISTWDotNETScript item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object vIndex);


  /**
   * <p>
   * Method Add
   * </p>
   * @param bstrName Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWDotNETScript
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  com.borland.silktest.workbench.generated.ISTWDotNETScript add(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrName);


  // Properties:
}
