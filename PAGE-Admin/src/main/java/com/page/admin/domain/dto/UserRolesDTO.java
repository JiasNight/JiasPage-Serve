package com.page.admin.domain.dto;

public class UserRolesDTO {

  /** 用户id */
  private String userId;

  /** 角色 */
  private String[] roles;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String[] getRoles() {
    return roles;
  }

  public void setRoles(String[] roles) {
    this.roles = roles;
  }
}
