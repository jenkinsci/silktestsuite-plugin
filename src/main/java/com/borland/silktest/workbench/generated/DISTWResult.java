package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.Holder;
import com4j.IID;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWResult extends Com4jObject {
  // Methods:
  /**
   * @param lStartingEvent Mandatory java.lang.Object parameter.
   */

  @DISPID(10)
  boolean open(
    java.lang.Object lStartingEvent);


  /**
   * @param versionNumber Mandatory int parameter.
   * @param xmLversion Mandatory int parameter.
   * @param xmLfileName Mandatory Holder<java.lang.String> parameter.
   * @param xslTfileName Mandatory java.lang.String parameter.
   * @param pbRet Mandatory Holder<Boolean> parameter.
   */

  @DISPID(11)
  int getXML(
    int versionNumber,
    int xmLversion,
    Holder<java.lang.String> xmLfileName,
    java.lang.String xslTfileName,
    Holder<Boolean> pbRet);


  /**
   * @param xsltVersion Mandatory int parameter.
   * @param xmlString Mandatory Holder<java.lang.String> parameter.
   * @param pbRet Mandatory Holder<Boolean> parameter.
   */

  @DISPID(12)
  int getXSLT(
    int xsltVersion,
    Holder<java.lang.String> xmlString,
    Holder<Boolean> pbRet);


  /**
   * @param versionNumber Mandatory int parameter.
   * @param fileName Mandatory Holder<java.lang.String> parameter.
   * @param pbRet Mandatory Holder<Boolean> parameter.
   */

  @DISPID(13)
  int getErrorScreen(
    int versionNumber,
    Holder<java.lang.String> fileName,
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

}
