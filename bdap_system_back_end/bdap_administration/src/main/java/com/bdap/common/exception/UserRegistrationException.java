package com.bdap.common.exception;

public class UserRegistrationException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public UserRegistrationException() {
    super();
  }

  public UserRegistrationException(String s) {
    super(s);
  }
}
