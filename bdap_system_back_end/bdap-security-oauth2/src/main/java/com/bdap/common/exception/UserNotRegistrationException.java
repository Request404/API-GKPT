package com.bdap.common.exception;

public class UserNotRegistrationException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public UserNotRegistrationException() {
    super();
  }

  public UserNotRegistrationException(String s) {
    super(s);
  }
}
