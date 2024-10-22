package com.page.admin.service;

import com.page.admin.domain.dto.UserInfoDTO;
import com.page.admin.domain.dto.UserQueryDTO;
import com.page.admin.domain.dto.UserRolesDTO;
import com.page.common.domain.PageResult;
import com.page.common.domain.QueryPage;
import com.page.common.domain.entity.SysUser;
import com.page.common.utils.resultUtil.Result;

public interface ISysUserService {

  /**
   * 获取用户列表
   *
   * @param queryPage 条件查询参数
   * @return 分页列表
   */
  PageResult getUserList(QueryPage<UserQueryDTO> queryPage);

  /**
   * 添加用户
   *
   * @param user 用户实体
   * @return boolean
   */
  Result addUserInfo(SysUser user);

  /**
   * 删除用户
   *
   * @param userId 用户id
   * @return boolean
   */
  Result delUserInfo(String userId);

  /**
   * 修改用户
   *
   * @param userInfoDTO 用户信息
   * @return boolean
   */
  Result updateUserInfo(UserInfoDTO userInfoDTO);

  /**
   * 修改用户的角色
   *
   * @param userRolesDTO
   * @return
   */
  Result modifyUserInfo(UserRolesDTO userRolesDTO);

  /**
   * 查询用户信息
   *
   * @param userId 用户id
   * @return map
   */
  Result selectUserInfoById(String userId);
}
