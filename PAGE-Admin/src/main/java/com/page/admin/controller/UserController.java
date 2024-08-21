package com.page.admin.controller;

import com.page.admin.domain.entity.User;
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

  @Operation(summary = "获取用户列表")
  @PostMapping("/list")
  public Result getUserList(@RequestBody UserPageVo userPageVo) {
    return userService.getUserList(userPageVo);
  }

  @Operation(summary = "用户新增")
  @PostMapping("/add")
  public Result newAddUser(@RequestBody User user) {
    return userService.addUserInfo(user);
  }

  @Operation(summary = "修改用户")
  @PostMapping("/update")
  public Result updateUser(@RequestBody User user) {
    return userService.updateUserInfo(user);
  }

  @Operation(summary = "删除用户")
  @GetMapping("/delete/{uId}")
  public Result delUser(@PathVariable("uId") String uId) {
    return userService.delUserInfo(uId);
  }
}
