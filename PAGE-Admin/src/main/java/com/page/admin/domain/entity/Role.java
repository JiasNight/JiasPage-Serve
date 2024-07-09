package com.page.admin.domain.entity;

import com.page.common.domain.BaseEntity;

/**
 * @author JSON
 * @date 2024/7/8
 * @description 角色实体
 */
public class Role extends BaseEntity {

  /** 主键id */
  private String id;

  /** 角色代码 */
  private String code;

  /** 角色名称 */
  private String name;

  /** 角色描述 */
  private String description;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
