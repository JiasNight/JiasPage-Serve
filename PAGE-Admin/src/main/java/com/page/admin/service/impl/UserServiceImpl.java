package com.page.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.page.admin.domain.dto.UserQueryDto;
import com.page.admin.domain.vo.UserListVo;
import com.page.admin.mapper.UserMapper;
import com.page.admin.service.IUserService;
import com.page.common.base.BaseService;
import com.page.common.configuration.TransferConfiguration;
import com.page.common.domain.PageResult;
import com.page.common.domain.QueryPage;
import com.page.common.domain.entity.SysUser;
import com.page.common.utils.cryptionUtil.AesUtil;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class UserServiceImpl extends BaseService implements IUserService {

  @Resource UserMapper userMapper;

  @Resource TransferConfiguration transferConfiguration;

  /**
   * 获取用户信息列表
   *
   * @param queryPage 查询条件和分页
   * @return 用户信息列表
   */
  @Override
  public PageResult getUserList(QueryPage<UserQueryDto> queryPage) {
    IPage<UserListVo> page = new Page<>(queryPage.getPageNum(), queryPage.getPageSize());
    List<UserListVo> userList =
        userMapper.selectUserList(
            page, queryPage.getQuery(), StrUtil.toUnderlineCase(queryPage.getSortBy()));
    PageResult pageResult = new PageResult();
    pageResult.setTotal((long) userList.size());
    pageResult.setRecords(userList);
    pageResult.setPages(page.getPages());
    return pageResult;
  }

  /**
   * 添加用户信息
   *
   * @param user 用户实体
   * @return boolean
   */
  @Override
  public Result addUserInfo(SysUser user) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
      // user.setUserId(UUID.randomUUID().toString());
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

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
      SysUser user = new SysUser();
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
  public Result updateUserInfo(SysUser user) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
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
