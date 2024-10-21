package com.page.admin.service.impl;

import com.page.admin.domain.vo.RoleListVO;
import com.page.admin.mapper.SysRoleMapper;
import com.page.admin.service.ISysRoleService;
import com.page.common.domain.PageResult;
import com.page.common.domain.entity.SysRole;
import com.page.common.domain.entity.SysUser;
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
public class SysRoleServiceImpl implements ISysRoleService {

  @Autowired SysRoleMapper sysRoleMapper;

  @Override
  public PageResult<RoleListVO> getRoleList() {
    List<RoleListVO> roleList = sysRoleMapper.getRoleList();
    PageResult<RoleListVO> pageResult = new PageResult<>();
    pageResult.setRecords(roleList);
    return pageResult;
  }

  @Override
  public Result addRoleInfo(SysRole sysRole) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      // sysRole.setId(UUID.randomUUID().toString());
      sysRole.setCreateBy(sysUser.getUsername());
      sysRole.setCreateTime(time);
      sysRole.setUpdateTime(time);
      sysRole.setUpdateBy(sysUser.getUsername());
      sysRole.setIsDeleted(0);
      int i = sysRoleMapper.addRoleInfo(sysRole);
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
  public Result updateRoleInfo(SysRole roleInfo) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    try {
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      roleInfo.setUpdateTime(time);
      roleInfo.setUpdateBy(sysUser.getUsername());
      int i = sysRoleMapper.updateRoleInfo(roleInfo);
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
      int i = sysRoleMapper.delRoleInfo(rId);
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
