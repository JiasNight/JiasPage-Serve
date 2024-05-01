package com.jias.page.service.impl;

import com.jias.page.config.TransferConfig;
import com.jias.page.domain.SignInUser;
import com.jias.page.exception.ServiceException;
import com.jias.page.mapper.SignInMapper;
import com.jias.page.service.ISignInService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.jwtUtil.JWTUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.enums.ResultEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JIAS
 */
@Service
public class SignInServiceImpl implements ISignInService {

  @Resource
  SignInMapper signInMapper;

  @Autowired
  TransferConfig transferConfig;

  @Autowired RedisUtil redisUtil;

  /**
   * 用户是否登录
   *
   * @param signInUser 用户登录信息
   * @return boolean
   */
  @Override
  public Map<String, Object> userIsSigIn(SignInUser signInUser) {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    JWTUtil jwtUtil = new JWTUtil();
    Map<String, Object> userMap = new HashMap<String, Object>();
    Map<String, Object> resultMap = new HashMap<String, Object>();

    SignInUser userInfo = signInMapper.getUserByUsername(signInUser.getUsername());

    if (userInfo == null) {
      resultMap.put("token", null);
      resultMap.put("isSignIn", "false");
      resultMap.put("code", ResultEnum.USER_ACCOUNT_NOT_EXIST.getCode());
      return resultMap;
    }

    userMap.put("userId", userInfo.getUserId());
    userMap.put("username", userInfo.getUsername());
    userMap.put("password", userInfo.getPassword());
    String resultPassword = null;
    try {
      // 使用AES解密前端传递过来的加密字符串
      resultPassword = AESUtil.decrypt(signInUser.getPassword(), transferConfig.getAesKey());
    } catch (Exception e) {
      throw new ServiceException(e.getMessage());
    }
    //    判断是否进行加密请求
    boolean matches = bCryptPasswordEncoder.matches(resultPassword, userInfo.getPassword());
    System.out.println("matches:" + matches);
    if (matches) {
      String token =
          jwtUtil.createJwt(userInfo.getUserId(), userInfo.getUsername(), userMap, 36000L);
      redisUtil.set(userInfo.getUserId(), token, 300);
      resultMap.put("token", token);
      resultMap.put("isSignIn", "true");
      resultMap.put("code", ResultEnum.SUCCESS);
    } else {
      resultMap.put("token", null);
      resultMap.put("isSignIn", "false");
      resultMap.put("code", ResultEnum.USER_CREDENTIALS_ERROR);
    }
    return resultMap;
  }
}
