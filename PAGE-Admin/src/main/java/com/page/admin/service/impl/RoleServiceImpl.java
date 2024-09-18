package com.page.admin.service.impl;

import com.page.admin.domain.entity.Role;
import com.page.admin.domain.vo.RoleListVo;
import com.page.admin.mapper.RoleMapper;
import com.page.admin.service.IRoleService;
import com.page.auth.domain.entity.SysUser;
import com.page.common.domain.PageResult;
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
  public PageResult<RoleListVo> getRoleList() {
    List<RoleListVo> roleList = roleMapper.getRoleList();
    PageResult<RoleListVo> pageResult = new PageResult<>();
    pageResult.setRecords(roleList);
    return pageResult;
  }

  @Override
  public Result addRoleInfo(Role role) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
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
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
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
