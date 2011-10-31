package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWPlaybackEnvironment extends Com4jObject {
  // Methods:
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

}
