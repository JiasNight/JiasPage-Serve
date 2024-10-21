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
@TableName("sys_role")
public class SysRole extends BaseEntity {

  /** 角色id */
  @TableId(value = "id", type = IdType.ASSIGN_UUID)
  private String id;

  /** 角色代码 */
  @TableField("code")
  private String code;

  /** 角色名称 */
  @TableField("name")
  private String name;

  /** 角色描述 */
  @TableField("description")
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
