package com.jias.page.service;

import com.jias.page.domain.SignInUser;
import com.jias.page.domain.SysUser;
import com.jias.page.domain.vo.UserInfo;

import java.util.Map;

public interface ISysUserService {


    String userSigIn(SignInUser signInUser);

  /** 获取用户信息 */
  UserInfo getUserInfo(String token);

  /** 获取用户信息通过userId */
  UserInfo getUserById(String userId);

 /** 通过用户名获取用户 */
 SysUser getUserByUsername(String username);
}
