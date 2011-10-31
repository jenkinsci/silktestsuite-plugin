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
public interface DISTWApp extends Com4jObject {
  // Methods:
  /**
   * @param bstrUserName Mandatory java.lang.String parameter.
   * @param bstrPwd Mandatory java.lang.String parameter.
   * @param bstrDatabase Optional parameter. Default value is com4j.Variant.getMissing()
   * @param bSilent Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(9)
  boolean login(
    java.lang.String bstrUserName,
    java.lang.String bstrPwd,
    @Optional java.lang.Object bstrDatabase,
    @Optional java.lang.Object bSilent);


  /**
   */

  @DISPID(10)
  boolean logout();


  /**
   * @param bstrProjectName Mandatory java.lang.String parameter.
   * @param bstrAssetName Mandatory java.lang.String parameter.
   * @param bAppendToResult Optional parameter. Default value is 0
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(11)
  boolean execute(
    java.lang.String bstrProjectName,
    java.lang.String bstrAssetName,
    @Optional @DefaultValue("0") java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") java.lang.Object bIncrementCycle,
    @Optional java.lang.Object bstrPlaybackEnvironment);


  /**
   */

  @DISPID(12)
  void stop();


  /**
   */

  @DISPID(13)
  void show();


  /**
   */

  @DISPID(14)
  void hide();


  /**
   * @param bstrProjectName Mandatory java.lang.String parameter.
   * @param bstrAssetName Mandatory java.lang.String parameter.
   * @param lngVersion Mandatory int parameter.
   * @param eatAssetType Mandatory int parameter.
   */

  @DISPID(15)
  int assetIDFromNameAndVersion(
    java.lang.String bstrProjectName,
    java.lang.String bstrAssetName,
    int lngVersion,
    int eatAssetType);


  /**
   * @param strUserName Mandatory java.lang.String parameter.
   * @param strPwd Mandatory java.lang.String parameter.
   * @param eDBType Mandatory com.borland.silktest.workbench.generated.enuDatabaseTypes parameter.
   * @param strErrorString Mandatory Holder<java.lang.String> parameter.
   * @param strDSNname Mandatory java.lang.Object parameter.
   * @param strDBuserName Mandatory java.lang.String parameter.
   * @param strDBpassword Mandatory java.lang.String parameter.
   * @param strDBserver Mandatory java.lang.String parameter.
   * @param strDBname Mandatory java.lang.String parameter.
   * @param strDBfullPath Mandatory java.lang.String parameter.
   * @param strODBCINIDriver Mandatory java.lang.String parameter.
   * @param strDBschemaOwner Mandatory java.lang.String parameter.
   * @param bSilent Mandatory java.lang.Object parameter.
   * @param bCheckSchema Mandatory java.lang.Object parameter.
   * @param eLogin Mandatory com.borland.silktest.workbench.generated.enuLoginTypes parameter.
   */

  @DISPID(16)
  boolean login2(
    java.lang.String strUserName,
    java.lang.String strPwd,
    com.borland.silktest.workbench.generated.enuDatabaseTypes eDBType,
    Holder<java.lang.String> strErrorString,
    java.lang.Object strDSNname,
    java.lang.String strDBuserName,
    java.lang.String strDBpassword,
    java.lang.String strDBserver,
    java.lang.String strDBname,
    java.lang.String strDBfullPath,
    java.lang.String strODBCINIDriver,
    java.lang.String strDBschemaOwner,
    java.lang.Object bSilent,
    java.lang.Object bCheckSchema,
    com.borland.silktest.workbench.generated.enuLoginTypes eLogin);


  // Properties:
  /**
   * <p>
   * Getter method for the COM property "PlaybackInProgress"
   * </p>
   * @return The COM property PlaybackInProgress as a boolean
   */
  @DISPID(1)
  @PropGet
  boolean getPlaybackInProgress();

  /**
   * <p>
   * Setter method for the COM property "PlaybackInProgress"
   * </p>
   * @param newValue The new value for the COM property PlaybackInProgress as a boolean
   */
  @DISPID(1)
  @PropPut
  void setPlaybackInProgress(boolean newValue);

  /**
   * <p>
   * Getter method for the COM property "MaxVersion"
   * </p>
   * @return The COM property MaxVersion as a int
   */
  @DISPID(2)
  @PropGet
  int getMaxVersion();

  /**
   * <p>
   * Setter method for the COM property "MaxVersion"
   * </p>
   * @param newValue The new value for the COM property MaxVersion as a int
   */
  @DISPID(2)
  @PropPut
  void setMaxVersion(int newValue);

  /**
   * <p>
   * Getter method for the COM property "MinVersion"
   * </p>
   * @return The COM property MinVersion as a int
   */
  @DISPID(3)
  @PropGet
  int getMinVersion();

  /**
   * <p>
   * Setter method for the COM property "MinVersion"
   * </p>
   * @param newValue The new value for the COM property MinVersion as a int
   */
  @DISPID(3)
  @PropPut
  void setMinVersion(int newValue);

  /**
   * <p>
   * Getter method for the COM property "Projects"
   * </p>
   * @return The COM property Projects as a com4j.Com4jObject
   */
  @DISPID(4)
  @PropGet
  com4j.Com4jObject getProjects();

  /**
   * <p>
   * Setter method for the COM property "Projects"
   * </p>
   * @param newValue The new value for the COM property Projects as a com4j.Com4jObject
   */
  @DISPID(4)
  @PropPut
  void setProjects(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "LastResult"
   * </p>
   * @return The COM property LastResult as a com4j.Com4jObject
   */
  @DISPID(6)
  @PropGet
  com4j.Com4jObject getLastResult();

  /**
   * <p>
   * Setter method for the COM property "LastResult"
   * </p>
   * @param newValue The new value for the COM property LastResult as a com4j.Com4jObject
   */
  @DISPID(6)
  @PropPut
  void setLastResult(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "PlaybackEnvironments"
   * </p>
   * @return The COM property PlaybackEnvironments as a com4j.Com4jObject
   */
  @DISPID(5)
  @PropGet
  com4j.Com4jObject getPlaybackEnvironments();

  /**
   * <p>
   * Setter method for the COM property "PlaybackEnvironments"
   * </p>
   * @param newValue The new value for the COM property PlaybackEnvironments as a com4j.Com4jObject
   */
  @DISPID(5)
  @PropPut
  void setPlaybackEnvironments(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "hWnd"
   * </p>
   * @return The COM property hWnd as a int
   */
  @DISPID(7)
  @PropGet
  int getHWnd();

  /**
   * <p>
   * Setter method for the COM property "hWnd"
   * </p>
   * @param newValue The new value for the COM property hWnd as a int
   */
  @DISPID(7)
  @PropPut
  void setHWnd(int newValue);

  /**
   * <p>
   * Getter method for the COM property "DatabaseItems"
   * </p>
   * @return The COM property DatabaseItems as a com4j.Com4jObject
   */
  @DISPID(8)
  @PropGet
  com4j.Com4jObject getDatabaseItems();

  /**
   * <p>
   * Setter method for the COM property "DatabaseItems"
   * </p>
   * @param newValue The new value for the COM property DatabaseItems as a com4j.Com4jObject
   */
  @DISPID(8)
  @PropPut
  void setDatabaseItems(com4j.Com4jObject newValue);

}
