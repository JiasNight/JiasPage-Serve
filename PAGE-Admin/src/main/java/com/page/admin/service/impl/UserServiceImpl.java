package com.page.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.page.admin.domain.entity.User;
import com.page.admin.domain.vo.UserListVo;
import com.page.admin.domain.vo.UserPageVo;
import com.page.admin.mapper.UserMapper;
import com.page.admin.service.IUserService;
import com.page.auth.domain.SysUser;
import com.page.common.configuration.TransferConfiguration;
import com.page.common.utils.cryptionUtil.AesUtil;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JIAS
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

  @Resource UserMapper userMapper;

  @Resource TransferConfiguration transferConfiguration;

  /**
   * 获取用户信息列表
   *
   * @param userPageVo 分页
   * @return 用户信息列表
   */
  @Override
  public Result getUserList(UserPageVo userPageVo) {
    try {
      Page<UserListVo> page = new Page<>();
      page.setSize(userPageVo.getPageSize());
      page.setCurrent(userPageVo.getPageNum());
      IPage<UserListVo> userList = userMapper.selectUserList(page);
      return Result.success(userList);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  /**
   * 添加用户信息
   *
   * @param user 用户实体
   * @return boolean
   */
  @Override
  public Result addUserInfo(User user) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      user.setUserId(UUID.randomUUID().toString());
      user.setCreateBy(sysUser.getUsername());
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      user.setCreateTime(time);
      user.setUpdateBy(sysUser.getUsername());
      user.setUpdateTime(time);
      user.setIsDeleted(0);
      String md5Password = AesUtil.decrypt(user.getPassword(), transferConfiguration.getAesKey());
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      String encodePassword = bCryptPasswordEncoder.encode(md5Password);
      user.setPassword(encodePassword);
      int i = userMapper.addUserInfo(user);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  /**
   * 删除用户
   *
   * @param userId 用户id
   * @return boolean
   */
  @Override
  public Result delUserInfo(String userId) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      User user = new User();
      user.setUserId(userId);
      user.setIsDeleted(1);
      user.setUpdateBy(sysUser.getUsername());
      user.setUpdateTime(
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
      int i = userMapper.delUserInfo(user);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  /**
   * 修改用户
   *
   * @param user 用户实体
   * @return boolean
   */
  @Override
  public Result updateUserInfo(User user) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      user.setUpdateBy(sysUser.getUsername());
      user.setUpdateTime(
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
      int i = userMapper.updateUserInfo(user);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  /**
   * 查询用户信息
   *
   * @param userId 用户id
   * @return 用户信息
   */
  @Override
  public Result selectUserInfoById(String userId) {
    Map map;
    try {
      map = userMapper.selectUserInfoById(userId);
      return Result.success(map);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }
}
