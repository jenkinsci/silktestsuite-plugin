package com.borland.silktest.workbench.generated  ;

import com4j.ComEnum;

/**
 * <p>
 * Result Enumeration
 * </p>
 */
public enum enuResult implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  eResult_None(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  eResult_Fail(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  eResult_Pass(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  eResult_PlaybackError(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  eResult_PlaybackErrorScreen(5),
  ;

  private final int value;
  enuResult(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
