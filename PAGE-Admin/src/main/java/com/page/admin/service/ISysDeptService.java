package com.page.admin.service;

import com.page.common.domain.entity.SysDept;
import com.page.common.utils.resultUtil.Result;

public interface ISysDeptService {

  /**
   * 获取角色列表
   *
   * @param
   * @return
   */
  Result getDeptData();

  Result addDeptInfo(SysDept sysDept);

  Result updateDeptInfo(SysDept sysDept);

  Result delDeptInfo(String dId);
}
