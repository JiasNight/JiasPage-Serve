package com.page.admin.controller;

import com.page.admin.domain.User;
import com.page.admin.domain.vo.UserPageVo;
import com.page.admin.service.IUserService;
import com.page.common.utils.resultUtil.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {

  @Autowired IUserService userService;

  @PostMapping("/list")
  public Result getUserList(@RequestBody UserPageVo userPageVo) {
    System.out.println(userPageVo.getPageSize());
    return userService.getUserList(userPageVo);
  }

  @Operation(summary = "用户注册")
  @PostMapping("/signUp")
  public Result newAddUser(@RequestBody User user) {
    return userService.addUserInfo(user);
  }
}
