package com.page.auth.domain;

public class SysUser {

  /** 用户id */
  private String userId;

  /** 用户名 */
  private String username;

  /** 用户密码 */
  private String password;


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

}
