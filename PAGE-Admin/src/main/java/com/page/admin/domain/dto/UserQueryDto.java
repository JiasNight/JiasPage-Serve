package com.page.admin.domain.dto;

import com.page.common.domain.QueryPage;

/**
 * @author JSON
 * @date 2024/4/18
 * @description
 */
public class UserQueryDto extends QueryPage {

  private String deptId;

  private String username;

  private String nickName;

  private String phone;

  private String role;

  private String createDate;

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
}
