package com.page.admin.service;

import com.page.admin.domain.vo.RoleListVo;
import com.page.common.domain.PageResult;
import com.page.common.domain.entity.SysRole;
import com.page.common.utils.resultUtil.Result;

public interface IRoleService {

  /**
   * 获取角色列表
   *
   * @param
   * @return
   */
  PageResult<RoleListVo> getRoleList();

  Result addRoleInfo(SysRole sysRole);

  Result updateRoleInfo(SysRole sysRole);

  Result delRoleInfo(String rId);
}
