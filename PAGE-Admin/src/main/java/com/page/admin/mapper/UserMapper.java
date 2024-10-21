package com.page.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.page.admin.domain.dto.UserQueryDto;
import com.page.admin.domain.vo.UserListVo;
import com.page.common.domain.entity.SysUser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

  /**
   * 获取用户列表
   *
   * @return
   */
  List<UserListVo> selectUserList(
      @Param("page") IPage<UserListVo> page,
      @Param("userQueryDto") UserQueryDto userQueryDto,
      @Param("sort") String sort);

  /**
   * 添加用户
   *
   * @param user
   * @return
   */
  int addUserInfo(SysUser user);

  /**
   * 删除用户
   *
   * @param user
   * @return
   */
  int delUserInfo(SysUser user);

  /**
   * 修改用户
   *
   * @param user
   * @return
   */
  int updateUserInfo(SysUser user);

  Map selectUserInfoById(@Param("userId") String userId);
}
