package com.page.admin.domain.entity;

import com.page.common.domain.BaseEntity;

public class User extends BaseEntity {

  /** 用户id */
  private String userId;

  /** 用户账号 */
  private String username;

  /** 密码 */
  private String password;

  /** 用户昵称 */
  private String nickname;

  /** 电子邮箱 */
  private String email;

  /** 电话号码 */
  private String phone;

  /** 用户性别，0男，1女 */
  private Integer gender;

  /** 出生日期 */
  private String birthday;

  /** 城市id */
  private Integer cityId;

  /** 用户头像 */
  private String avatar;

  /** 用户状态，0正常，1停用 */
  private Integer status;

  /** 备注 */
  private String remarks;

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

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
