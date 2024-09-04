package com.page.auth.service.impl;

import com.page.auth.domain.MyUserDetails;
import com.page.auth.domain.entity.SysUser;
import com.page.auth.service.IMyUserDetailsService;
import com.page.auth.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JSON
 * @date 2024/6/7
 * @description
 */
@Service
public class MyUserDetailsServiceImpl implements IMyUserDetailsService {
  /*
   * UserDetailsService：提供查询用户功能，如根据用户名查询用户，并返回UserDetails
   * UserDetails，SpringSecurity定义的类， 记录用户信息，如用户名、密码、权限等
   *
   */

  @Autowired ISysUserService sysUserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser sysUser = sysUserService.getUserByUsername(username);
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    MyUserDetails myUserDetails = new MyUserDetails(sysUser, authorities);
    return myUserDetails;
  }
}
