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
@TableName("sys_dept")
public class SysDept extends BaseEntity {

  /** 部门id，主键 */
  @TableId(value = "id", type = IdType.ASSIGN_UUID)
  private String id;

  /** 父id */
  @TableField("pid")
  private String pid;

  /** 部门代码 */
  @TableField("code")
  private String code;

  /** 部门名称 */
  @TableField("name")
  private String name;

  /** 部门描述 */
  @TableField("description")
  private String description;

  /** 部门状态,0启用，1未启用 */
  @TableField("status")
  private int status;

  /** 部门排序 */
  @TableField("order")
  private int order;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
