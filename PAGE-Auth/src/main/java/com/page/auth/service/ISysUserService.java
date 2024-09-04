package com.page.auth.service;

import com.page.auth.domain.entity.SysUser;
import com.page.auth.domain.vo.UserInfoVo;

public interface ISysUserService {

  /** 获取用户信息通过userId */
  UserInfoVo getUserInfoById(String userId);

  /** 通过用户名获取用户 */
  SysUser getUserByUsername(String username);
}
