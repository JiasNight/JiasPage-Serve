package com.page.admin.domain.entity;

import com.page.common.domain.BaseEntity;

/**
 * @author JSON
 * @date 2024/7/9
 * @description
 */
public class Dept extends BaseEntity {
    /**
     * 部门id，主键
     */
    private String id;

    /**
     * 父id
     */
    private String pid;

    /**
     * 部门代码
     */
    private String code;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门描述
     */
    private String description;

    /**
     * 部门状态,0启用，1未启用
     */
    private int status;

    /**
     * 部门排序
     */
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
