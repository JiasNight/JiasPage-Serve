package com.jias.page.service;

import com.jias.page.domain.SignInUser;

public interface ISignInService {

  /**
   * 用户登陆
   *
   * @param signInUser 用户信息
   * @return boolean
   */
  boolean userIsSigIn(SignInUser signInUser);
}
