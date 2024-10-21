package com.page.admin.domain.vo;

import com.page.common.domain.entity.SysDept;
import java.util.List;

/**
 * @author JSON
 * @date 2024/7/9
 * @description
 */
public class DeptVo extends SysDept {

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
