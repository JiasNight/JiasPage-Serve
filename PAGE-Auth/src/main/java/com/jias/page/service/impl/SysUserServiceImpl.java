package com.jias.page.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jias.page.config.TransferConfig;
import com.jias.page.domain.SignInUser;
import com.jias.page.domain.SysUser;
import com.jias.page.domain.vo.UserInfo;
import com.jias.page.exception.CustomException;
import com.jias.page.exception.GlobalException;
import com.jias.page.exception.ServiceException;
import com.jias.page.mapper.SysUserMapper;
import com.jias.page.service.ISysUserService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.jwtUtil.JWTUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.enums.ResultEnum;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserServiceImpl implements ISysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired RedisUtil redisUtil;

  @Autowired
  JWTUtil jwtUtil;

  @Autowired
  TransferConfig transferConfig;

  /**
   * 用户是否登录
   *
   * @param signInUser 用户登录
   * @return boolean
   */
  @Override
  public String userSigIn(SignInUser signInUser) {
    Logger logger = LoggerFactory.getLogger(getClass());
    // UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInUser.getUsername(), signInUser.getPassword());
    // Authentication authenticate = authenticationManager.authenticate(authenticationToken);
    // if (authenticate == null) {
    //   logger.error("{username: {}, password: {}} 认证失败！");
    //   return null;
    // }

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    JWTUtil jwtUtil = new JWTUtil();
    Map<String, Object> userMap = new HashMap<String, Object>();

    SignInUser userInfo = sysUserMapper.getUserByUsername(signInUser.getUsername());

    if (userInfo == null) {
      throw new ServiceException(ResultEnum.USER_ACCOUNT_NOT_EXIST.getMessage());
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
    boolean matches = bCryptPasswordEncoder.matches(resultPassword, userInfo.getPassword());
    if (matches) {
      String token =
              jwtUtil.createJwt(userInfo.getUserId(), userInfo.getUsername(), userMap);
      redisUtil.set(userInfo.getUserId(), token, 300);
      return token;
    } else {
      return null;
    }
  }

  @Override
  public UserInfo getUserInfo(String token) {
    try {
      Claims claims = jwtUtil.parseJwt(token);
      if (claims == null) {
        throw new CustomException(ResultEnum.ERROR.getCode(), "token过期");
      }
      String userId = claims.get("userId").toString();
      UserInfo userInfoByUserId = sysUserMapper.getUserInfoByUserId(userId);
      return userInfoByUserId;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException(
          ResultEnum.SELECT_EXCEPTION.getCode(), ResultEnum.SELECT_EXCEPTION.getMessage());
    }
  }

  @Override
  public UserInfo getUserById(String userId) {
    return sysUserMapper.getUserInfoByUserId(userId);
  }

  @Override
  public SysUser getUserByUsername(String username) {
    SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
            .eq(username != null, SysUser::getUsername, username));

    if (sysUser==null){
      throw new UsernameNotFoundException("用户不存在");
    }
    return sysUser;
  }
}
