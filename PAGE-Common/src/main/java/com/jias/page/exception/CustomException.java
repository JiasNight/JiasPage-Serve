package com.jias.page.exception;

public class CustomException extends RuntimeException {

  private int code;

  private String message;

  /**
   * 自定义异常类
   *
   * @param message
   */
  public CustomException(String message) {
    super(message);
  }

  public CustomException(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
