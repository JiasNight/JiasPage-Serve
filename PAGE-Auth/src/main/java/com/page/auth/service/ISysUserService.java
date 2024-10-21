package com.page.auth.service;

import com.page.auth.domain.vo.UserInfoVo;
import com.page.common.domain.entity.SysUser;

public interface ISysUserService {

  /** 获取用户信息通过userId */
  UserInfoVo getUserInfoById(String userId);

  /** 通过用户名获取用户 */
  SysUser getUserByUsername(String username);
}
