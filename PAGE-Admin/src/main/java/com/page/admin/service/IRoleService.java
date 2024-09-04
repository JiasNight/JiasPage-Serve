package com.page.admin.service;

import com.page.admin.domain.entity.Role;
import com.page.admin.domain.vo.RoleListVo;
import com.page.common.domain.PageResult;
import com.page.common.utils.resultUtil.Result;

import java.util.List;

public interface IRoleService {

  /**
   * 获取角色列表
   *
   * @param
   * @return
   */
  PageResult<RoleListVo> getRoleList();

  Result addRoleInfo(Role role);

  Result updateRoleInfo(Role role);

  Result delRoleInfo(String rId);
}
