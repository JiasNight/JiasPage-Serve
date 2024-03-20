package com.jias.page.service.impl;

import com.jias.page.domain.SignInUser;
import com.jias.page.mapper.SignInMapper;
import com.jias.page.service.ISignInService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author JIAS
 */
@Service
public class SignInServiceImpl implements ISignInService {

  @Resource SignInMapper signInMapper;

  /**
   * 用户是否登录
   *
   * @param signInUser 用户登录信息
   * @return boolean
   */
  @Override
  public boolean userIsSigIn(SignInUser signInUser) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    SignInUser userInfo = signInMapper.getUserByUsername(signInUser.getUsername());
    return false;
  }
}
