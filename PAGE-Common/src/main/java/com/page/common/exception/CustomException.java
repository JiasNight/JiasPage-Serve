package com.page.common.exception;

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

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
