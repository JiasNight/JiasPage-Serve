package com.jias.page.service.impl;

import com.jias.page.domain.User;
import com.jias.page.mapper.LoginMapper;
import com.jias.page.service.ILoginService;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.tokenUtil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author JIAS
 */
@Service
public class LoginServiceImpl implements ILoginService {

  @Resource LoginMapper loginMapper;

  @Autowired RedisUtil redisUtil;

  /**
   * 用户是否登录
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  @Override
  public Map<String, String> userIsSignIn(String userName, String password) {
    Map signInInfo = new HashMap();
    try {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      Map userList = loginMapper.userIsSignIn(userName);
      boolean isHave =
          bCryptPasswordEncoder.matches(password, userList.get("userPassword").toString());
      if (isHave) {
        TokenUtil tokenUtil = new TokenUtil();
        String token = tokenUtil.generateToken(userName);

        // 把token存到redis中
        redisUtil.set("pageToken", token, 60);
        String userId = userList.get("userId").toString();
        List users = loginMapper.getUserInfo(userId);
        System.out.println(users.get(0));
        signInInfo.put("isSignIn", "1");
        signInInfo.put("userInfo", users.get(0));
        return signInInfo;
      } else {
        signInInfo.put("isSignIn", "0");
        signInInfo.put("userInfo", null);
        return signInInfo;
      }
    } catch (Exception e) {
      signInInfo.put("isSignIn", "0");
      signInInfo.put("userInfo", null);
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
      User user = new User();
      user.setUserId(UUID.randomUUID().toString());
      user.setUserName(userName);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      String userPassword = bCryptPasswordEncoder.encode(password);
      user.setUserPassword(userPassword);
      user.setCreateBy("admin");
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      user.setCreateTime(time);
      user.setUpdateTime(time);
      int isSuccess = loginMapper.userAdd(user);
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
