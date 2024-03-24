package com.jias.page.domain;

public class SignInUser {

  /** 用户id */
  private String userId;

  /** 用户名 */
  private String username;

  /** 用户密码 */
  private String password;

  /** 验证码 */
  private String code;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
