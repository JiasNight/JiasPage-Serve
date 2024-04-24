package com.jias.page.service.impl;

import com.jias.page.domain.User;
import com.jias.page.mapper.SignInMapper;
import com.jias.page.service.ISignInService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.jwtUtil.JwtUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author JIAS
 */
@ConfigurationProperties(prefix = "cryption")
@Service
public class SignInServiceImpl implements ISignInService {

  private static final Logger logger = LoggerFactory.getLogger(ISignInService.class);

  private static final String AES_KEY = "def009A1bcO538JK";

  @Resource
  SignInMapper signInMapper;

  @Autowired RedisUtil redisUtil;

  /**
   * 用户是否登录
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  @Override
  public Map<String, Object> userIsSignIn(String userName, String password) {
    Map<String, Object> signInInfo = new HashMap();
    try {
      // 使用AES解密前端传递过来的加密字符串
      String resultPassword = AESUtil.decrypt(password, AES_KEY);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      List<User> userList = signInMapper.userIsSignIn(userName);
      User resultUser = new User();
      Map<String, Object> resultMap = new HashMap();
      boolean isHave = false;
      for (int i = 0; i < userList.size(); i++) {
        isHave = bCryptPasswordEncoder.matches(resultPassword, userList.get(i).getUserPassword());
        resultUser = userList.get(i);
        resultMap.put("userName", userList.get(i).getUserName());
        resultMap.put("userId", userList.get(i).getUserId());
        resultMap.put("userPhone", userList.get(i).getUserPhone());
        if (isHave) break;
      }
      if (isHave) {
        JwtUtil jwtUtil = new JwtUtil();
        String token =
            jwtUtil.createJwt(resultUser.getUserId(), resultUser.getUserName(), resultMap, 36000L);
        // 把token存到redis中
        redisUtil.set("pageToken", token, 60);
        signInInfo.put("isSignIn", true);
        signInInfo.put("token", token);
        return signInInfo;
      } else {
        signInInfo.put("isSignIn", false);
        signInInfo.put("token", null);
        return signInInfo;
      }
    } catch (Exception e) {
      signInInfo.put("isSignIn", false);
      signInInfo.put("token", null);
      return signInInfo;
    }
  }

  /**
   * 用户添加
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  @Override
  public boolean userAdd(String userName, String password) {
    try {
      String resultPassword = AESUtil.decrypt(password, AES_KEY);
      User user = new User();
      user.setUserId(UUID.randomUUID().toString());
      user.setUserName(userName);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      String userPassword = bCryptPasswordEncoder.encode(resultPassword);
      user.setUserPassword(userPassword);
      user.setCreateBy("admin");
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      user.setCreateTime(time);
      user.setUpdateTime(time);
      int isSuccess = signInMapper.userAdd(user);
      if (isSuccess > 0) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}
