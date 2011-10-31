package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.VTID;

/**
 * SilkTest Workbench Asset Interface
 */
@IID("{1C7D5FFC-1E23-4A58-AF95-CA495C861E33}")
public interface _ISTWAsset extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Property Name
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.String name();


  /**
   * <p>
   * Property Description
   * </p>
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String description();


  /**
   * <p>
   * Property Project
   * </p>
   * <p>
   * Getter method for the COM property "Project"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWProject
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  com.borland.silktest.workbench.generated.ISTWProject project();


  /**
   * <p>
   * Property CreatedBy
   * </p>
   * <p>
   * Getter method for the COM property "CreatedBy"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String createdBy();


  /**
   * <p>
   * Property CreationDate
   * </p>
   * <p>
   * Getter method for the COM property "CreationDate"
   * </p>
   * @return  Returns a value of type java.util.Date
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  java.util.Date creationDate();


  /**
   * <p>
   * Property LastModifiedBy
   * </p>
   * <p>
   * Getter method for the COM property "LastModifiedBy"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String lastModifiedBy();


  /**
   * <p>
   * Property LastModifiedDate
   * </p>
   * <p>
   * Getter method for the COM property "LastModifiedDate"
   * </p>
   * @return  Returns a value of type java.util.Date
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  java.util.Date lastModifiedDate();


  /**
   * <p>
   * Property CurrentVersion
   * </p>
   * <p>
   * Getter method for the COM property "CurrentVersion"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  int currentVersion();


  /**
   * <p>
   * Property AssetID
   * </p>
   * <p>
   * Getter method for the COM property "AssetID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(15)
  int assetID();


  // Properties:
}
