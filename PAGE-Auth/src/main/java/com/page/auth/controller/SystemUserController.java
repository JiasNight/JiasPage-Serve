package com.page.auth.controller;

import com.page.auth.domain.vo.UserInfoVo;
import com.page.auth.service.ISystemUserService;
import com.page.common.domain.entity.SysUser;
import com.page.common.utils.resultUtil.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Tag(name = "登陆验证")
@RequestMapping("/user")
public class SystemUserController {

  @Autowired ISystemUserService systemUserService;

  @Operation(summary = "通过token获取用户信息")
  @GetMapping("/getUserInfo")
  public Result getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SysUser sysUser = (SysUser) authentication.getPrincipal();
    UserInfoVo sysUserInfoVo = systemUserService.getUserInfoById(sysUser.getUserId());
    return Result.success(sysUserInfoVo);
  }
}
