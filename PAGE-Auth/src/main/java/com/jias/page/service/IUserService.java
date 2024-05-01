package com.jias.page.service;

import com.jias.page.domain.vo.UserInfo;

public interface IUserService {

  /** 获取用户信息 */
  UserInfo getUserInfo(String token);

  /** 获取用户信息通过userId */
  UserInfo getUserById(String userId);

 /** 通过用户名获取用户 */
 UserInfo getUserByUsername(String username);
}
