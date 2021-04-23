package com.bdap.common.exception;

public class FeignInvokeException extends RuntimeException{

  private final static long serialVersionUID = 1L;

  public FeignInvokeException() {
    super();
  }

  public FeignInvokeException(String s) {
    super(s);
  }

}
