package com.jias.page.service;

import java.util.Map;

public interface ISignInService {

  /**
   * 用户登陆
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  Map<String, Object> userIsSignIn(String userName, String password);

  /**
   * 用户注册
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  boolean userAdd(String userName, String password);
}
