package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.DefaultValue;
import com4j.Holder;
import com4j.IID;
import com4j.Optional;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWVisualTest extends Com4jObject {
  // Methods:
  /**
   */

  @DISPID(11)
  boolean open();


  /**
   * @param bAppendToResult Optional parameter. Default value is -1
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(12)
  boolean execute(
    @Optional @DefaultValue("-1") java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") java.lang.Object bIncrementCycle,
    @Optional java.lang.Object bstrPlaybackEnvironment);


  /**
   * <p>
   * Getter method for the COM property "ParameterItem"
   * </p>
   * @param pvIndex Mandatory java.lang.Object parameter.
   */

  @DISPID(13)
  @PropGet
  com4j.Com4jObject parameterItem(
    java.lang.Object pvIndex);


  /**
   * @param nItemNumber Mandatory int parameter.
   * @param pbRet Mandatory Holder<Boolean> parameter.
   */

  @DISPID(14)
  int openToItem(
    int nItemNumber,
    Holder<Boolean> pbRet);


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
   * Getter method for the COM property "Project"
   * </p>
   * @return The COM property Project as a com4j.Com4jObject
   */
  @DISPID(3)
  @PropGet
  com4j.Com4jObject getProject();

  /**
   * <p>
   * Setter method for the COM property "Project"
   * </p>
   * @param newValue The new value for the COM property Project as a com4j.Com4jObject
   */
  @DISPID(3)
  @PropPut
  void setProject(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "CreatedBy"
   * </p>
   * @return The COM property CreatedBy as a java.lang.String
   */
  @DISPID(4)
  @PropGet
  java.lang.String getCreatedBy();

  /**
   * <p>
   * Setter method for the COM property "CreatedBy"
   * </p>
   * @param newValue The new value for the COM property CreatedBy as a java.lang.String
   */
  @DISPID(4)
  @PropPut
  void setCreatedBy(java.lang.String newValue);

  /**
   * <p>
   * Getter method for the COM property "CreationDate"
   * </p>
   * @return The COM property CreationDate as a java.util.Date
   */
  @DISPID(5)
  @PropGet
  java.util.Date getCreationDate();

  /**
   * <p>
   * Setter method for the COM property "CreationDate"
   * </p>
   * @param newValue The new value for the COM property CreationDate as a java.util.Date
   */
  @DISPID(5)
  @PropPut
  void setCreationDate(java.util.Date newValue);

  /**
   * <p>
   * Getter method for the COM property "LastModifiedBy"
   * </p>
   * @return The COM property LastModifiedBy as a java.lang.String
   */
  @DISPID(6)
  @PropGet
  java.lang.String getLastModifiedBy();

  /**
   * <p>
   * Setter method for the COM property "LastModifiedBy"
   * </p>
   * @param newValue The new value for the COM property LastModifiedBy as a java.lang.String
   */
  @DISPID(6)
  @PropPut
  void setLastModifiedBy(java.lang.String newValue);

  /**
   * <p>
   * Getter method for the COM property "LastModifiedDate"
   * </p>
   * @return The COM property LastModifiedDate as a java.util.Date
   */
  @DISPID(7)
  @PropGet
  java.util.Date getLastModifiedDate();

  /**
   * <p>
   * Setter method for the COM property "LastModifiedDate"
   * </p>
   * @param newValue The new value for the COM property LastModifiedDate as a java.util.Date
   */
  @DISPID(7)
  @PropPut
  void setLastModifiedDate(java.util.Date newValue);

  /**
   * <p>
   * Getter method for the COM property "CurrentVersion"
   * </p>
   * @return The COM property CurrentVersion as a int
   */
  @DISPID(8)
  @PropGet
  int getCurrentVersion();

  /**
   * <p>
   * Setter method for the COM property "CurrentVersion"
   * </p>
   * @param newValue The new value for the COM property CurrentVersion as a int
   */
  @DISPID(8)
  @PropPut
  void setCurrentVersion(int newValue);

  /**
   * <p>
   * Getter method for the COM property "AssetID"
   * </p>
   * @return The COM property AssetID as a int
   */
  @DISPID(9)
  @PropGet
  int getAssetID();

  /**
   * <p>
   * Setter method for the COM property "AssetID"
   * </p>
   * @param newValue The new value for the COM property AssetID as a int
   */
  @DISPID(9)
  @PropPut
  void setAssetID(int newValue);

  /**
   * <p>
   * Getter method for the COM property "ParameterCount"
   * </p>
   * @return The COM property ParameterCount as a int
   */
  @DISPID(10)
  @PropGet
  int getParameterCount();

  /**
   * <p>
   * Setter method for the COM property "ParameterCount"
   * </p>
   * @param newValue The new value for the COM property ParameterCount as a int
   */
  @DISPID(10)
  @PropPut
  void setParameterCount(int newValue);

}
