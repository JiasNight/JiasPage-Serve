package com.jias.page.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jias.page.domain.SignInUser;
import com.jias.page.domain.SysUser;
import com.jias.page.domain.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

  UserInfo getUserInfoByUserId(@Param("userId") String userId);

  SignInUser getUserByUsername(@Param("username") String username);
}
