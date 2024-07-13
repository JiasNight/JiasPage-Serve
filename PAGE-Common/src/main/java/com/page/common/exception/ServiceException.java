package com.page.common.exception;

public class ServiceException extends RuntimeException {

  private int code;

  private String message;

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
