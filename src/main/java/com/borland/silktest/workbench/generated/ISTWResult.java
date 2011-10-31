package com.borland.silktest.workbench.generated  ;

import com4j.DISPID;
import com4j.DefaultValue;
import com4j.Holder;
import com4j.IID;
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.Optional;
import com4j.VTID;

/**
 * SilkTest Workbench Result Interface
 */
@IID("{112EE061-3D84-4F83-97F6-2C9B48A16E5D}")
public interface ISTWResult extends com.borland.silktest.workbench.generated._ISTWAsset {
  // Methods:
  /**
   * <p>
   * Method Open
   * </p>
   * @param lStartingEvent Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  boolean open(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object lStartingEvent);


  /**
   * <p>
   * Method GetXML
   * </p>
   * @param versionNumber Mandatory int parameter.
   * @param xmLversion Mandatory int parameter.
   * @param xmLfileName Mandatory Holder<java.lang.String> parameter.
   * @param fileName Optional parameter. Default value is ""
   * @return  Returns a value of type int
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  int getXML(
    int versionNumber,
    int xmLversion,
    Holder<java.lang.String> xmLfileName,
    @Optional @DefaultValue("") java.lang.String fileName);


  /**
   * <p>
   * Method GetXSLT
   * </p>
   * @param xsltVersion Mandatory int parameter.
   * @param xslt Mandatory Holder<java.lang.String> parameter.
   * @return  Returns a value of type int
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  int getXSLT(
    int xsltVersion,
    Holder<java.lang.String> xslt);


  /**
   * <p>
   * Method GetErrorScreen
   * </p>
   * @param versionNumber Mandatory int parameter.
   * @param fileName Mandatory Holder<java.lang.String> parameter.
   * @return  Returns a value of type int
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(19)
  int getErrorScreen(
    int versionNumber,
    Holder<java.lang.String> fileName);


  // Properties:
}
