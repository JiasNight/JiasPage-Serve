package com.jias.page.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jias.page.domain.vo.UserInfo;
import com.jias.page.exception.CustomException;
import com.jias.page.exception.ServiceException;
import com.jias.page.mapper.UserInfoMapper;
import com.jias.page.service.IUserService;
import com.jias.page.utils.jwtUtil.JWTUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.enums.ResultEnum;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Resource
  UserInfoMapper userInfoMapper;

  @Autowired RedisUtil redisUtil;

  @Autowired
  JWTUtil jwtUtil;

  @Override
  public UserInfo getUserInfo(String token) {
    try {
      Claims claims = jwtUtil.parseJwt(token);
      if (claims == null) {
        throw new CustomException(ResultEnum.ERROR.getCode(), "token过期");
      }
      String userId = claims.get("userId").toString();
      UserInfo userInfoByUserId = userInfoMapper.getUserInfoByUserId(userId);
      return userInfoByUserId;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException(
          ResultEnum.SELECT_EXCEPTION.getCode(), ResultEnum.SELECT_EXCEPTION.getMessage());
    }
  }

  @Override
  public UserInfo getUserById(String userId) {
    return userInfoMapper.getUserInfoByUserId(userId);
  }

  @Override
  public UserInfo getUserByUsername(String username) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("username", username);

  }
}
