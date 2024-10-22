package com.page.admin.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.page.admin.domain.dto.UserInfoDTO;
import com.page.admin.domain.dto.UserQueryDTO;
import com.page.admin.domain.dto.UserRolesDTO;
import com.page.admin.domain.vo.UserListVO;
import com.page.admin.mapper.SysUserMapper;
import com.page.admin.mapper.SysUserRoleMapper;
import com.page.admin.service.ISysUserService;
import com.page.common.base.BaseService;
import com.page.common.configuration.TransferConfiguration;
import com.page.common.domain.PageResult;
import com.page.common.domain.QueryPage;
import com.page.common.domain.entity.SysUser;
import com.page.common.domain.entity.SysUserRole;
import com.page.common.utils.cryptionUtil.AesUtil;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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
public class SysUserServiceImpl extends BaseService implements ISysUserService {

  @Resource SysUserMapper sysUserMapper;

  @Resource SysUserRoleMapper sysUserRoleMapper;

  @Resource TransferConfiguration transferConfiguration;

  /**
   * 获取用户信息列表
   *
   * @param queryPage 查询条件和分页
   * @return 用户信息列表
   */
  @Override
  public PageResult getUserList(QueryPage<UserQueryDTO> queryPage) {
    IPage<UserListVO> page = new Page<>(queryPage.getPageNum(), queryPage.getPageSize());
    List<UserListVO> userList =
        sysUserMapper.selectUserList(
            page, queryPage.getQuery(), StrUtil.toUnderlineCase(queryPage.getSortBy()));
    for (UserListVO userListVO : userList) {
      List<SysUserRole> userRoleList =
          sysUserRoleMapper.getUserRoleListByUserId(userListVO.getUserId());
      if (userRoleList.size() > 0) {
        List<String> roleArray = new ArrayList();
        for (int i = 0; i < userRoleList.size(); i++) {
          roleArray.add(userRoleList.get(i).getRoleId());
          userListVO.setRoles(roleArray.toArray(new String[userRoleList.size()]));
        }
      }
    }
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
      List<UserListVO> hasList = sysUserMapper.selectUserByUsername(user.getUsername());
      if (hasList.size() > 0) {
        return Result.failure("该用户账号已经注册！");
      }
      String userId = UUID.randomUUID().toString();
      user.setUserId(userId);
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
      int i = sysUserMapper.addUserInfo(user);
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
      int i = sysUserMapper.delUserInfo(user);
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
   * @param userInfoDTO 用户信息
   * @return boolean
   */
  @Override
  public Result updateUserInfo(UserInfoDTO userInfoDTO) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
      SysUser user = new SysUser();
      BeanUtils.copyProperties(userInfoDTO, user);
      user.setUpdateBy(sysUser.getUsername());
      user.setUpdateTime(
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
      int i = sysUserMapper.updateUserInfo(user);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result modifyUserInfo(UserRolesDTO userRolesDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
      String curUserId = userRolesDTO.getUserId();
      String[] roles = userRolesDTO.getRoles();
      // 删除该用户id绑定的角色
      sysUserRoleMapper.deleteRolesByUserId(curUserId);
      List userRoleList = new ArrayList();
      for (String role : roles) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(role);
        sysUserRole.setUserId(curUserId);
        sysUserRole.setCreateBy(sysUser.getUsername());
        sysUserRole.setCreateTime(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        sysUserRole.setUpdateBy(sysUser.getUsername());
        sysUserRole.setUpdateTime(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        userRoleList.add(sysUserRole);
      }
      int i = sysUserRoleMapper.batchInsertUserRole(userRoleList);
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
      map = sysUserMapper.selectUserInfoById(userId);
      return Result.success(map);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }
}
