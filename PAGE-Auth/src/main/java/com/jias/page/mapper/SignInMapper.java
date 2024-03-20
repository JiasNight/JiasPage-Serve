package com.jias.page.mapper;

import com.jias.page.domain.SignInUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignInMapper {

  /**
   * 用户登陆,这里包括了前台和后台管理部分
   *
   * @param username
   * @return
   */
  SignInUser getUserByUsername(@Param("username") String username);
}
