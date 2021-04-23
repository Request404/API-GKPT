package com.bdap.traffic.system.common.exception;

public class LectureNotFoundException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public LectureNotFoundException() {
    super();
  }

  public LectureNotFoundException(String s) {
    super(s);
  }
}
