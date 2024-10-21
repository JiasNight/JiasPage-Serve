package com.page.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.page.auth.domain.vo.UserInfoVo;
import com.page.auth.mapper.SystemUserMapper;
import com.page.auth.service.ISystemUserService;
import com.page.common.configuration.TransferConfiguration;
import com.page.common.domain.entity.SysUser;
import com.page.common.utils.jwtUtil.JwtUtil;
import com.page.common.utils.redisUtil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements ISystemUserService {

  @Autowired private SystemUserMapper systemUserMapper;

  @Autowired RedisUtil redisUtil;

  @Autowired JwtUtil jwtUtil;

  @Autowired TransferConfiguration transferConfiguration;

  @Override
  public UserInfoVo getUserInfoById(String userId) {
    return systemUserMapper.getUserInfoByUserId(userId);
  }

  @Override
  public SysUser getUserByUsername(String username) {
    SysUser sysUser =
        systemUserMapper.selectOne(
            new LambdaQueryWrapper<SysUser>().eq(username != null, SysUser::getUsername, username));

    if (sysUser == null) {
      throw new UsernameNotFoundException("用户不存在");
    }
    return sysUser;
  }
}
