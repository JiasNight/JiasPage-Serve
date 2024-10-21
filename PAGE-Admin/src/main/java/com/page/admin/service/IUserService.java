package com.page.admin.service;

import com.page.admin.domain.dto.UserQueryDto;
import com.page.common.domain.PageResult;
import com.page.common.domain.QueryPage;
import com.page.common.domain.entity.SysUser;
import com.page.common.utils.resultUtil.Result;

public interface IUserService {

  /**
   * 获取用户列表
   *
   * @param queryPage 条件查询参数
   * @return 分页列表
   */
  PageResult getUserList(QueryPage<UserQueryDto> queryPage);

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
   * @param user 用户实体
   * @return boolean
   */
  Result updateUserInfo(SysUser user);

  /**
   * 查询用户信息
   *
   * @param userId 用户id
   * @return map
   */
  Result selectUserInfoById(String userId);
}
