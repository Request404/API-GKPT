package com.bdap.common.exception;

/**
 * 用户找不到异常
 */
public class UserNotFoundException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String s) {
    super(s);
  }
}
