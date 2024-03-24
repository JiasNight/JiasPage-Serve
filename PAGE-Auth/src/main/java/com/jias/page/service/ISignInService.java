package com.jias.page.service;

import com.jias.page.domain.SignInUser;

import java.util.Map;

public interface ISignInService {

  /**
   * 用户登陆
   *
   * @param signInUser 用户信息
   * @return boolean
   */
  Map<String, String> userIsSigIn(SignInUser signInUser);
}
