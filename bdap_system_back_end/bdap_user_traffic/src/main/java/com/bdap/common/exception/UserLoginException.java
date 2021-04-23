package com.bdap.common.exception;

public class UserLoginException extends RuntimeException{

  private final static long serialVersionUID = 1L;

  public UserLoginException() {
    super();
  }

  public UserLoginException(String s) {
    super(s);
  }
}
