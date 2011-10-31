package com.borland.silktest.workbench.generated  ;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.IID;
import com4j.PropGet;
import com4j.PropPut;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface DISTWExecutionResult extends Com4jObject {
  // Methods:
  // Properties:
  /**
   * <p>
   * Getter method for the COM property "Result"
   * </p>
   * @return The COM property Result as a int
   */
  @DISPID(1)
  @PropGet
  int getResult();

  /**
   * <p>
   * Setter method for the COM property "Result"
   * </p>
   * @param newValue The new value for the COM property Result as a int
   */
  @DISPID(1)
  @PropPut
  void setResult(int newValue);

  /**
   * <p>
   * Getter method for the COM property "StartingEvent"
   * </p>
   * @return The COM property StartingEvent as a int
   */
  @DISPID(2)
  @PropGet
  int getStartingEvent();

  /**
   * <p>
   * Setter method for the COM property "StartingEvent"
   * </p>
   * @param newValue The new value for the COM property StartingEvent as a int
   */
  @DISPID(2)
  @PropPut
  void setStartingEvent(int newValue);

  /**
   * <p>
   * Getter method for the COM property "VerificationsFailed"
   * </p>
   * @return The COM property VerificationsFailed as a int
   */
  @DISPID(3)
  @PropGet
  int getVerificationsFailed();

  /**
   * <p>
   * Setter method for the COM property "VerificationsFailed"
   * </p>
   * @param newValue The new value for the COM property VerificationsFailed as a int
   */
  @DISPID(3)
  @PropPut
  void setVerificationsFailed(int newValue);

  /**
   * <p>
   * Getter method for the COM property "PlaybackErrorString"
   * </p>
   * @return The COM property PlaybackErrorString as a java.lang.String
   */
  @DISPID(4)
  @PropGet
  java.lang.String getPlaybackErrorString();

  /**
   * <p>
   * Setter method for the COM property "PlaybackErrorString"
   * </p>
   * @param newValue The new value for the COM property PlaybackErrorString as a java.lang.String
   */
  @DISPID(4)
  @PropPut
  void setPlaybackErrorString(java.lang.String newValue);

  /**
   * <p>
   * Getter method for the COM property "ResultObject"
   * </p>
   * @return The COM property ResultObject as a com4j.Com4jObject
   */
  @DISPID(5)
  @PropGet
  com4j.Com4jObject getResultObject();

  /**
   * <p>
   * Setter method for the COM property "ResultObject"
   * </p>
   * @param newValue The new value for the COM property ResultObject as a com4j.Com4jObject
   */
  @DISPID(5)
  @PropPut
  void setResultObject(com4j.Com4jObject newValue);

  /**
   * <p>
   * Getter method for the COM property "VerificationPassCriteria"
   * </p>
   * @return The COM property VerificationPassCriteria as a int
   */
  @DISPID(6)
  @PropGet
  int getVerificationPassCriteria();

  /**
   * <p>
   * Setter method for the COM property "VerificationPassCriteria"
   * </p>
   * @param newValue The new value for the COM property VerificationPassCriteria as a int
   */
  @DISPID(6)
  @PropPut
  void setVerificationPassCriteria(int newValue);

  /**
   * <p>
   * Getter method for the COM property "VerificationsExecuted"
   * </p>
   * @return The COM property VerificationsExecuted as a int
   */
  @DISPID(7)
  @PropGet
  int getVerificationsExecuted();

  /**
   * <p>
   * Setter method for the COM property "VerificationsExecuted"
   * </p>
   * @param newValue The new value for the COM property VerificationsExecuted as a int
   */
  @DISPID(7)
  @PropPut
  void setVerificationsExecuted(int newValue);

  /**
   * <p>
   * Getter method for the COM property "VerificationsPassed"
   * </p>
   * @return The COM property VerificationsPassed as a int
   */
  @DISPID(8)
  @PropGet
  int getVerificationsPassed();

  /**
   * <p>
   * Setter method for the COM property "VerificationsPassed"
   * </p>
   * @param newValue The new value for the COM property VerificationsPassed as a int
   */
  @DISPID(8)
  @PropPut
  void setVerificationsPassed(int newValue);

}
