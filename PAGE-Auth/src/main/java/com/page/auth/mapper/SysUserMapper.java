package com.page.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.page.auth.domain.entity.SysUser;
import com.page.auth.domain.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

  UserInfoVo getUserInfoByUserId(@Param("userId") String userId);
}
