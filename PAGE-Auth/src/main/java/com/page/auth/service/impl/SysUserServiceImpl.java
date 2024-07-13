package com.page.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.page.auth.domain.SysUser;
import com.page.auth.domain.vo.UserInfo;
import com.page.auth.mapper.SysUserMapper;
import com.page.auth.service.ISysUserService;
import com.page.common.configuration.TransferConfiguration;
import com.page.common.utils.jwtUtil.JwtUtil;
import com.page.common.utils.redisUtil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {

  @Autowired private SysUserMapper sysUserMapper;

  @Autowired RedisUtil redisUtil;

  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  TransferConfiguration transferConfiguration;

  @Override
  public UserInfo getUserInfoById(String userId) {
    return sysUserMapper.getUserInfoByUserId(userId);
  }

  @Override
  public SysUser getUserByUsername(String username) {
    SysUser sysUser =
        sysUserMapper.selectOne(
            new LambdaQueryWrapper<SysUser>().eq(username != null, SysUser::getUsername, username));

    if (sysUser == null) {
      throw new UsernameNotFoundException("用户不存在");
    }
    return sysUser;
  }
}
