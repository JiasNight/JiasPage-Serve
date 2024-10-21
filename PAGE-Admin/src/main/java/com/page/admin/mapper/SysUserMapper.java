package com.page.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.page.admin.domain.dto.UserQueryDTO;
import com.page.admin.domain.vo.UserListVO;
import com.page.common.domain.entity.SysUser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

  /**
   * 获取用户列表
   *
   * @return
   */
  List<UserListVO> selectUserList(
      @Param("page") IPage<UserListVO> page,
      @Param("userQueryDto") UserQueryDTO userQueryDto,
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

  /**
   * 查询用户信息通过id
   *
   * @param userId
   * @return
   */
  Map selectUserInfoById(@Param("userId") String userId);

  /**
   * 查询有无已经注册的账号
   *
   * @param username
   * @return
   */
  List<UserListVO> selectUserByUsername(@Param("username") String username);
}
