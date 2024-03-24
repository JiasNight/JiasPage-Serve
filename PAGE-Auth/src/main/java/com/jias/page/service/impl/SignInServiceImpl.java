package com.jias.page.service.impl;

import com.jias.page.config.TransferConfig;
import com.jias.page.domain.SignInUser;
import com.jias.page.mapper.SignInMapper;
import com.jias.page.service.ISignInService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.jwtUtil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JIAS
 */
@Service
public class SignInServiceImpl implements ISignInService {

  private static final String AES_KEY = "def009A1bcO538JK";

  @Resource SignInMapper signInMapper;

  @Autowired TransferConfig transferConfig;

  /**
   * 用户是否登录
   *
   * @param signInUser 用户登录信息
   * @return boolean
   */
  @Override
  public Map<String, String> userIsSigIn(SignInUser signInUser) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    JwtUtil jwtUtil = new JwtUtil();
    Map<String, Object> userMap = new HashMap<String, Object>();
    Map<String, String> resultMap = new HashMap<String, String>();

    SignInUser userInfo = signInMapper.getUserByUsername(signInUser.getUsername());

    if (userInfo == null) {
      resultMap.put("token", null);
      resultMap.put("isSignIn", "false");
      return resultMap;
    }

    userMap.put("userId", userInfo.getUserId());
    userMap.put("username", userInfo.getUsername());
    userMap.put("password", userInfo.getPassword());
    //    判断是否进行加密请求
    if (transferConfig.getRequestSafe()) {
      // 使用AES解密前端传递过来的加密字符串
      String resultPassword = AESUtil.decrypt(signInUser.getPassword(), transferConfig.getAesKey());
      boolean matches = bCryptPasswordEncoder.matches(resultPassword, userInfo.getPassword());
      if (matches) {
        String token =
            jwtUtil.createJwt(userInfo.getUserId(), userInfo.getUsername(), userMap, 36000L);

        resultMap.put("token", token);
        resultMap.put("isSignIn", "true");
      } else {
        resultMap.put("token", null);
        resultMap.put("isSignIn", "false");
      }
    } else {
      boolean matches =
          bCryptPasswordEncoder.matches(signInUser.getPassword(), userInfo.getPassword());
      if (matches) {
        String token =
            jwtUtil.createJwt(userInfo.getUserId(), userInfo.getUsername(), userMap, 36000L);
        resultMap.put("token", token);
        resultMap.put("isSignIn", "true");
      } else {
        resultMap.put("token", null);
        resultMap.put("isSignIn", "false");
      }
    }
    return resultMap;
  }
}
