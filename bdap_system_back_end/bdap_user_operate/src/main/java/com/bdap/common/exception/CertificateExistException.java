package com.bdap.common.exception;

public class CertificateExistException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public CertificateExistException() {
    super();
  }

  public CertificateExistException(String s) {
    super(s);
  }

}
