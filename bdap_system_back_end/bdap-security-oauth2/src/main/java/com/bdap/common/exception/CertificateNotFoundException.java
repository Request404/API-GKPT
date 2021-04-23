package com.bdap.common.exception;

public class CertificateNotFoundException extends RuntimeException {

  private final static long serialVersionUID = 1L;

  public CertificateNotFoundException() {
    super();
  }

  public CertificateNotFoundException(String s) {
    super(s);
  }
}
