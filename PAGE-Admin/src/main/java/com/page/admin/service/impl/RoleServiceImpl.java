package com.page.admin.service.impl;

import com.page.admin.domain.entity.Role;
import com.page.admin.mapper.RoleMapper;
import com.page.admin.service.IRoleService;
import com.page.auth.domain.SysUser;
import com.page.common.utils.resultUtil.Result;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

  @Autowired RoleMapper roleMapper;

  @Override
  public Result getRoleList() {
    List<Role> roleList;
    try {
      roleList = roleMapper.getRoleList();
      return Result.success(roleList);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result addRoleInfo(Role role) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      role.setId(UUID.randomUUID().toString());
      role.setCreateBy(sysUser.getUsername());
      role.setCreateTime(time);
      role.setUpdateTime(time);
      role.setUpdateBy(sysUser.getUsername());
      role.setIsDeleted(0);
      int i = roleMapper.addRoleInfo(role);
      // int i = roleMapper.insert(role);
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
  public Result updateRoleInfo(Role roleInfo) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      roleInfo.setUpdateTime(time);
      roleInfo.setUpdateBy(sysUser.getUsername());
      int i = roleMapper.updateRoleInfo(roleInfo);
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
  public Result delRoleInfo(String rId) {
    try {
      int i = roleMapper.delRoleInfo(rId);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }
}
