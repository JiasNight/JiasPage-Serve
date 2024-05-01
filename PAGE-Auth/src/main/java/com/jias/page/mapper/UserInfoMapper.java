package com.jias.page.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jias.page.domain.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

  UserInfo getUserInfoByUserId(@Param("userId") String userId);
}
