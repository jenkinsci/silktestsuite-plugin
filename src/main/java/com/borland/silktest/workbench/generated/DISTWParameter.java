package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWParameter extends Com4jObject {
  // Methods:
  /**
   * @param vValue Mandatory java.lang.Object parameter.
   */

  @DISPID(4)
  boolean setValue(
    java.lang.Object vValue);


  /**
   */

  @DISPID(5)
  java.lang.Object getValue();


  /**
   */

  @DISPID(6)
  java.lang.Object getDefaultValue();


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
   * Getter method for the COM property "Type"
   * </p>
   * @return The COM property Type as a com.borland.silktest.workbench.generated.enuParameterTypes
   */
  @DISPID(2)
  @PropGet
  com.borland.silktest.workbench.generated.enuParameterTypes getType();

  /**
   * <p>
   * Setter method for the COM property "Type"
   * </p>
   * @param newValue The new value for the COM property Type as a com.borland.silktest.workbench.generated.enuParameterTypes
   */
  @DISPID(2)
  @PropPut
  void setType(com.borland.silktest.workbench.generated.enuParameterTypes newValue);

  /**
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return The COM property Description as a java.lang.String
   */
  @DISPID(3)
  @PropGet
  java.lang.String getDescription();

  /**
   * <p>
   * Setter method for the COM property "Description"
   * </p>
   * @param newValue The new value for the COM property Description as a java.lang.String
   */
  @DISPID(3)
  @PropPut
  void setDescription(java.lang.String newValue);

}
