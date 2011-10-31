package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.VTID;

/**
 * SilkTest Workbench Database Collection Dual Interface
 */
@IID("{F794EFE5-8B10-42C4-BC0F-0FDE1D9BB40E}")
public interface ISTWDatabaseItems extends Com4jObject {
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
   * @param pvIndex Mandatory java.lang.Object parameter.
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWDatabaseItem
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  com.borland.silktest.workbench.generated.ISTWDatabaseItem item(
    java.lang.Object pvIndex);


  // Properties:
}
