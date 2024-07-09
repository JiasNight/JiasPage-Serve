package com.page.admin.domain.vo;

import com.page.admin.domain.entity.Dept;

import java.util.List;

/**
 * @author JSON
 * @date 2024/7/9
 * @description
 */
public class DeptVo extends Dept {

    /**
     * 子部门
     */
    private List<DeptVo> children;

    public List<DeptVo> getChildren() {
        return children;
    }

    public void setChildren(List<DeptVo> children) {
        this.children = children;
    }
}
