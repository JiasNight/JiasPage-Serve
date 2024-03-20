package com.jias.page.controller;

import com.jias.page.domain.SignInUser;
import com.jias.page.service.ISignInService;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(tags = "登陆验证")
@RequestMapping("/user")
public class SysSignInController {

  @Autowired ISignInService signInService;

  @ApiOperation(value = "用户登陆", notes = "用户登陆")
  @PostMapping("/singIn")
  public Result singIn(@RequestBody SignInUser signInUser) {
    try {
      boolean isSigIn = signInService.userIsSigIn(signInUser);
      if (isSigIn) {
        return Result.success();
      } else {
        return Result.failure("登陆失败");
      }
    } catch (Exception e) {
      return Result.failure(e.toString());
    }
  }
}
