package com.page.admin.service;

import com.page.admin.domain.entity.User;
import com.page.admin.domain.vo.UserPageVo;
import com.page.common.utils.resultUtil.Result;

public interface IUserService {

  /**
   * 获取用户列表
   *
   * @param userPageVo 条件查询参数
   * @return 分页列表
   */
  Result getUserList(UserPageVo userPageVo);

  /**
   * 添加用户
   *
   * @param user 用户实体
   * @return boolean
   */
  Result addUserInfo(User user);

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
  Result updateUserInfo(User user);

  /**
   * 查询用户信息
   *
   * @param userId 用户id
   * @return map
   */
  Result selectUserInfoById(String userId);
}
