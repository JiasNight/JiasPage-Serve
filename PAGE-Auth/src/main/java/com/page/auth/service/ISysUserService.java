package com.page.auth.service;

import com.page.auth.domain.SysUser;
import com.page.auth.domain.vo.UserInfo;

public interface ISysUserService {

  /** 获取用户信息通过userId */
  UserInfo getUserInfoById(String userId);

  /** 通过用户名获取用户 */
  SysUser getUserByUsername(String username);
}
