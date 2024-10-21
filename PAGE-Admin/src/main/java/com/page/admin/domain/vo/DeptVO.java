package com.page.admin.domain.vo;

import com.page.common.domain.entity.SysDept;
import java.util.List;

/**
 * @author JSON
 * @date 2024/7/9
 * @description
 */
public class DeptVO extends SysDept {

  /** 子部门 */
  private List<DeptVO> children;

  public List<DeptVO> getChildren() {
    return children;
  }

  public void setChildren(List<DeptVO> children) {
    this.children = children;
  }
}
