package com.bdap.common.exception;

public class ArgumentNotValidException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public ArgumentNotValidException() {
    super();
  }

  public ArgumentNotValidException(String s) {
    super(s);
  }
}
