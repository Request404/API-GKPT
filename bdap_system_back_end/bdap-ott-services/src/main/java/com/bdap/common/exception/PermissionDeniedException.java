package com.bdap.common.exception;

public class PermissionDeniedException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public PermissionDeniedException() {
    super();
  }

  public PermissionDeniedException(String s) {
    super(s);
  }
}
