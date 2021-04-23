package com.bdap.common.exception;

/**
 * 注册时用户存在异常
 */
public class UserExistException extends RuntimeException{

  private final static long serialVersionUID = 1L;

  public UserExistException() {
    super();
  }

  public UserExistException(String s) {
    super(s);
  }
}
