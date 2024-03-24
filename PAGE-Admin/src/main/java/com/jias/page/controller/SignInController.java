package com.jias.page.controller;

import com.jias.page.service.ISignInService;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import com.jias.page.utils.verifyUtil.ValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "登陆相关")
@RequestMapping("/user")
public class SignInController {

  @Autowired ISignInService signInService;

  @Autowired RedisUtil redisUtil;

  @ApiOperation(value = "后台用户登陆", notes = "后台用户登陆")
  @PostMapping("/signIn")
  public Result singIn(
      @RequestParam("userName") String userName, @RequestParam("password") String password) {
    try {
      Map<String, Object> signInInfo = signInService.userIsSignIn(userName, password);
      if ((Boolean) signInInfo.get("isSignIn")) {
        Map<String, String> tMap = new HashMap<String, String>();
        tMap.put("token", signInInfo.get("token").toString());
        return Result.success(tMap);
      } else {
        return Result.failure("登陆失败");
      }
    } catch (Exception e) {
      return Result.failure(e.toString());
    }
  }

  @ApiOperation(value = "用户注册", notes = "用户注册")
  @PostMapping("/add")
  public Result userRegister(
      @RequestParam("userName") String userName, @RequestParam("password") String password) {
    try {
      boolean isAdd = signInService.userAdd(userName, password);
      if (isAdd) {
        return Result.success();
      } else {
        return Result.failure("注册失败");
      }
    } catch (Exception e) {
      return Result.failure(e.toString());
    }
  }

  @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息")
  @GetMapping("/info")
  public Result getUserInfo() {
    try {
      Map userInfo = new HashMap();
      userInfo.put("userName", "admin");
      userInfo.put("userNick", "管理员");
      userInfo.put("userId", "tBTE8c4157XLITEMUrB1ayFuvWQH1cq8");
      return Result.success(userInfo);
    } catch (Exception e) {
      return Result.failure(e.toString());
    }
  }
}
