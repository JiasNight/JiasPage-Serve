package com.jias.page.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jias.page.domain.MyUserDetails;
import com.jias.page.domain.SysUser;
import com.jias.page.mapper.SysUserMapper;
import com.jias.page.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author JSON
 * @date 2024/4/29
 * @description
 */
@Component
@Service
public class MyUserDetailsService implements UserDetailsService {

    /*
    * UserDetailsService：提供查询用户功能，如根据用户名查询用户，并返回UserDetails
    * UserDetails，SpringSecurity定义的类， 记录用户信息，如用户名、密码、权限等
    *
     */

   @Autowired
   ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserByUsername(username);
        MyUserDetails myUserDetails = new MyUserDetails(sysUser);
        return myUserDetails;
    }
}
