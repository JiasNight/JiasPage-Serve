package com.jias.page.service;

import com.jias.page.domain.vo.UserInfo;

public interface IUserService {

  /** 获取用户信息 */
  UserInfo getUserInfo(String token);
}
