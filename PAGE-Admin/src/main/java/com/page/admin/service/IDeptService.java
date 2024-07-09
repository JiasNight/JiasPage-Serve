package com.page.admin.service;

import com.page.admin.domain.entity.Dept;
import com.page.admin.domain.entity.Role;
import com.page.common.utils.resultUtil.Result;

public interface IDeptService {

  /**
   * 获取角色列表
   *
   * @param
   * @return
   */
  Result getDeptData();

  Result addDeptInfo(Dept dept);

  Result updateDeptInfo(Dept dept);

  Result delDeptInfo(String dId);
}
