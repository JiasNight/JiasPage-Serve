package com.page.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.page.auth.domain.SysUser;
import com.page.auth.domain.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

  UserInfo getUserInfoByUserId(@Param("userId") String userId);
}
