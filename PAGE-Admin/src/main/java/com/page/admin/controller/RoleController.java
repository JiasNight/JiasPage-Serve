package com.page.admin.controller;

import com.page.admin.domain.dto.UserQueryDto;
import com.page.admin.domain.vo.RoleListVo;
import com.page.admin.service.IRoleService;
import com.page.common.domain.PageResult;
import com.page.common.domain.QueryPage;
import com.page.common.domain.entity.SysRole;
import com.page.common.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

  @Autowired IRoleService roleService;

  @PostMapping("/list")
  public Result getRoleList(@RequestBody QueryPage<UserQueryDto> queryPage) {
    PageResult<RoleListVo> roleList = roleService.getRoleList();
    return Result.success(roleList);
  }

  @PostMapping("/allList")
  public Result getAllRoleList() {
    PageResult<RoleListVo> roleList = roleService.getRoleList();
    return Result.success(roleList);
  }

  @PostMapping("/add")
  public Result addRole(@RequestBody SysRole sysRole) {
    return roleService.addRoleInfo(sysRole);
  }

  @PostMapping("/update")
  public Result updateRole(@RequestBody SysRole role) {
    return roleService.updateRoleInfo(role);
  }

  @GetMapping("/delete/{rId}")
  public Result delRole(@PathVariable("rId") String rId) {
    return roleService.delRoleInfo(rId);
  }
}
