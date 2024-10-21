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
@TableName("sys_permission")
public class SysPermission extends BaseEntity {

    /** 权限id */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /** 权限链接 */
    @TableField("url")
    private String url;

    /** 权限名称 */
    @TableField("name")
    private String name;

    /** 权限描述 */
    @TableField("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
