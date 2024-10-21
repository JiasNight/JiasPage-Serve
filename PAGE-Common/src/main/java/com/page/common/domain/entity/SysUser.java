package com.page.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.page.common.domain.BaseEntity;

/**
 * @author JSON
 * @date 2024/10/21
 * @description
 */
@TableName("sys_user")
public class SysUser extends BaseEntity {

  /** 用户id */
  @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
  private String userId;

  /** 用户账号 */
  @TableField("username")
  private String username;

  /** 密码 */
  @TableField("password")
  private String password;

  /** 用户昵称 */
  @TableField("nickname")
  private String nickname;

  /** 电子邮箱 */
  @TableField("email")
  private String email;

  /** 电话号码 */
  @TableField("phone")
  private String phone;

  /** 用户性别，0男，1女 */
  @TableField("gender")
  private Integer gender;

  /** 出生日期 */
  @TableField("birthday")
  private String birthday;

  /** 城市id */
  @TableField("city_id")
  private Integer cityId;

  /** 用户头像 */
  @TableField("avatar")
  private String avatar;

  /** 用户状态，0正常，1停用 */
  @TableField("status")
  private Integer status;

  /** 备注 */
  @TableField("remark")
  private String remark;

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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
