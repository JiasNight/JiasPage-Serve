package com.page.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.page.admin.domain.entity.User;
import com.page.admin.domain.vo.UserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

  /**
   * 获取用户列表
   *
   * @return
   */
  IPage<UserListVo> selectUserList(Page<UserListVo> page);

  /**
   * 添加用户
   *
   * @param user
   * @return
   */
  int addUserInfo(User user);

  /**
   * 删除用户
   *
   * @param user
   * @return
   */
  int delUserInfo(User user);

  /**
   * 修改用户
   *
   * @param user
   * @return
   */
  int updateUserInfo(User user);

  Map selectUserInfoById(@Param("userId") String userId);
}
