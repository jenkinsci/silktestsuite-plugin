package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.VTID;

/**
 * SilkTest Workbench Execution Result Interface
 */
@IID("{B340C7C6-62C1-48CE-8BBA-C5843EEA0FB3}")
public interface ISTWExecutionResult extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Property Result
   * </p>
   * <p>
   * Getter method for the COM property "Result"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.enuExecutionResult
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  com.borland.silktest.workbench.generated.enuExecutionResult result();


  /**
   * <p>
   * Property StartingEvent
   * </p>
   * <p>
   * Getter method for the COM property "StartingEvent"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  int startingEvent();


  /**
   * <p>
   * Property VerificationsFailed
   * </p>
   * <p>
   * Getter method for the COM property "VerificationsFailed"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  int verificationsFailed();


  /**
   * <p>
   * Property PlaybackErrorString
   * </p>
   * <p>
   * Getter method for the COM property "PlaybackErrorString"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String playbackErrorString();


  /**
   * <p>
   * Property Result (Returns a TPResult which is a TPAsset interface)
   * </p>
   * <p>
   * Getter method for the COM property "ResultObject"
   * </p>
   * @return  Returns a value of type com.borland.silktest.workbench.generated.ISTWResult
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  com.borland.silktest.workbench.generated.ISTWResult resultObject();


  /**
   * <p>
   * Property VerificationPassCriteria (Returns a long that represents the percentage of a pass criteria)
   * </p>
   * <p>
   * Getter method for the COM property "VerificationPassCriteria"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  int verificationPassCriteria();


  /**
   * <p>
   * Property VerificationsExecuted
   * </p>
   * <p>
   * Getter method for the COM property "VerificationsExecuted"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  int verificationsExecuted();


  /**
   * <p>
   * Property VerificationsPassed
   * </p>
   * <p>
   * Getter method for the COM property "VerificationsPassed"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  int verificationsPassed();


  // Properties:
}
