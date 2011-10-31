package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench Visual tests Collection Interface
 */
@IID("{110D101A-386C-49C8-AF0A-AC53B144A2C6}")
public interface ISTWVisualTests extends Com4jObject {
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
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWVisualTest
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  com.borland.silktest.workbench.generated.ISTWVisualTest item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object vIndex);


  /**
   * <p>
   * Method Add
   * </p>
   * @param bstrName Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWVisualTest
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  com.borland.silktest.workbench.generated.ISTWVisualTest add(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrName);


  // Properties:
}
