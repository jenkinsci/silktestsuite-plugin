package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.DefaultValue;
import com4j.Holder;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench Application Dual Interface
 */
@IID("{98CBFB8C-39A4-454D-A15D-28B841E18891}")
public interface ISTWApp extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Property PlaybackInProgress
   * </p>
   * <p>
   * Getter method for the COM property "PlaybackInProgress"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  boolean playbackInProgress();


  /**
   * <p>
   * Property MaxVersion
   * </p>
   * <p>
   * Getter method for the COM property "MaxVersion"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  int maxVersion();


  /**
   * <p>
   * Property MinVersion
   * </p>
   * <p>
   * Getter method for the COM property "MinVersion"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  int minVersion();


  /**
   * <p>
   * Property Projects
   * </p>
   * <p>
   * Getter method for the COM property "Projects"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWProjects
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  com.borland.silktest.workbench.generated.ISTWProjects projects();


  /**
   * <p>
   * Property LastResult
   * </p>
   * <p>
   * Getter method for the COM property "LastResult"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWExecutionResult
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  com.borland.silktest.workbench.generated.ISTWExecutionResult lastResult();


  /**
   * <p>
   * Property PlaybackEnvironments
   * </p>
   * <p>
   * Getter method for the COM property "PlaybackEnvironments"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWPlaybackEnvironments
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  com.borland.silktest.workbench.generated.ISTWPlaybackEnvironments playbackEnvironments();


  /**
   * <p>
   * Property HWnd
   * </p>
   * <p>
   * Getter method for the COM property "hWnd"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  int hWnd();


  /**
   * <p>
   * Property DatabaseItems
   * </p>
   * <p>
   * Getter method for the COM property "DatabaseItems"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWDatabaseItems
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  com.borland.silktest.workbench.generated.ISTWDatabaseItems databaseItems();


  /**
   * <p>
   * Method Login
   * </p>
   * @param bstrUserName Mandatory java.lang.String parameter.
   * @param bstrPwd Mandatory java.lang.String parameter.
   * @param bstrDatabase Optional parameter. Default value is com4j.Variant.getMissing()
   * @param bSilent Optional parameter. Default value is com4j.Variant.getMissing()
   * @param bCheckSchema Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(15)
  boolean login(
    java.lang.String bstrUserName,
    java.lang.String bstrPwd,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrDatabase,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bSilent,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bCheckSchema);


  /**
   * <p>
   * Method Logout
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  boolean logout();


  /**
   * <p>
   * Method Execute
   * </p>
   * @param bstrProjectName Mandatory java.lang.String parameter.
   * @param bstrAssetName Mandatory java.lang.String parameter.
   * @param bAppendToResult Optional parameter. Default value is -1
   * @param bIncrementCycle Optional parameter. Default value is 0
   * @param bstrPlaybackEnvironment Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  boolean execute(
    java.lang.String bstrProjectName,
    java.lang.String bstrAssetName,
    @Optional @DefaultValue("-1") @MarshalAs(NativeType.VARIANT) java.lang.Object bAppendToResult,
    @Optional @DefaultValue("0") @MarshalAs(NativeType.VARIANT) java.lang.Object bIncrementCycle,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object bstrPlaybackEnvironment);


  /**
   * <p>
   * Method Stop
   * </p>
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  void stop();


  /**
   * <p>
   * Method Show
   * </p>
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(19)
  void show();


  /**
   * <p>
   * Method Hide
   * </p>
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(20)
  void hide();


  /**
   * <p>
   * Method AssetIDFromNameAndVersion
   * </p>
   * @param bstrProjectName Mandatory java.lang.String parameter.
   * @param bstrAssetName Mandatory java.lang.String parameter.
   * @param lngVersion Mandatory int parameter.
   * @param eatAssetType Mandatory com.borland.silktest.workbench.generated.enuAssetType parameter.
   * @return  Returns a value of type int
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(21)
  int assetIDFromNameAndVersion(
    java.lang.String bstrProjectName,
    java.lang.String bstrAssetName,
    int lngVersion,
    com.borland.silktest.workbench.generated.enuAssetType eatAssetType);


  /**
   * <p>
   * Method Login2
   * </p>
   * @param strUserName Mandatory java.lang.String parameter.
   * @param bstrPwd Mandatory java.lang.String parameter.
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
   * @return  Returns a value of type boolean
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(22)
  boolean login2(
    java.lang.String strUserName,
    java.lang.String bstrPwd,
    com.borland.silktest.workbench.generated.enuDatabaseTypes eDBType,
    Holder<java.lang.String> strErrorString,
    @MarshalAs(NativeType.VARIANT) java.lang.Object strDSNname,
    java.lang.String strDBuserName,
    java.lang.String strDBpassword,
    java.lang.String strDBserver,
    java.lang.String strDBname,
    java.lang.String strDBfullPath,
    java.lang.String strODBCINIDriver,
    java.lang.String strDBschemaOwner,
    @MarshalAs(NativeType.VARIANT) java.lang.Object bSilent,
    @MarshalAs(NativeType.VARIANT) java.lang.Object bCheckSchema,
    com.borland.silktest.workbench.generated.enuLoginTypes eLogin);


  // Properties:
}
