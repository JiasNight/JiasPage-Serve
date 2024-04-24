package com.jias.page.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface LoginMapper {

    /**
     * 用户登陆
     * @param userName
     * @return
     */
    Map userIsSigIn(@Param("userName") String userName);

    int userAdd(User user);
}
