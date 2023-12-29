package com.jias.page.mapper;

import com.jias.page.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginMapper {

  /**
   * 用户登陆
   *
   * @param userName
   * @return
   */
  Map userIsSignIn(@Param("userName") String userName);

  int userAdd(User user);

  List<User> getUserInfo(@Param("userId") String userId);
}
