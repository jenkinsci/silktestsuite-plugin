package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.Optional;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWVisualTests extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param vIndex Mandatory java.lang.Object parameter.
   */

  @DISPID(2)
  @PropGet
  com4j.Com4jObject item(
    java.lang.Object vIndex);


  /**
   * @param bstrName Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(3)
  com4j.Com4jObject add(
    @Optional java.lang.Object bstrName);


  // Properties:
  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return The COM property Count as a int
   */
  @DISPID(1)
  @PropGet
  int getCount();

  /**
   * <p>
   * Setter method for the COM property "Count"
   * </p>
   * @param newValue The new value for the COM property Count as a int
   */
  @DISPID(1)
  @PropPut
  void setCount(int newValue);

}
