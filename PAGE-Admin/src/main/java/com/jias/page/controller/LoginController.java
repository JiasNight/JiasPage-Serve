package com.jias.page.controller;

import com.jias.page.service.ILoginService;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import com.jias.page.utils.tokenUtil.TokenUtil;
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
public class LoginController {

  @Autowired ILoginService loginService;

  @Autowired RedisUtil redisUtil;

  @ApiOperation(value = "后台用户登陆", notes = "后台用户登陆")
  @PostMapping("/login")
  public Result singIn(
      @RequestParam("userName") String userName, @RequestParam("password") String password) {
    try {
      Map<String, String> signInInfo = loginService.userIsSignIn(userName, password);
      if (signInInfo.get("isSignIn").toString().equals("1")) {
        Map tMap = new HashMap();
        tMap.put("token", signInInfo.get("token"));
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
      boolean isAdd = loginService.userAdd(userName, password);
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

  @ApiOperation(value = "登录验证码", notes = "登录验证码")
  @GetMapping("/validateCode")
  public Result getCode(
      HttpSession session, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("image/jpeg");
    // 禁止图像缓存。
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    ValidateCode vCode = new ValidateCode();
    // 将验证码存入Session
    session.setAttribute("imageCode", vCode.createImage()[0]);

    // 将图片转正base64
    BufferedImage image = (BufferedImage) vCode.createImage()[1];
    // 转base64
    Base64.Encoder encoder = Base64.getEncoder();
    ByteArrayOutputStream baos = new ByteArrayOutputStream(); // io流
    ImageIO.write(image, "png", baos); // 写入流中
    byte[] bytes = baos.toByteArray(); // 转换成字节
    String pngBase64 = encoder.encodeToString(bytes).trim(); // 转换成base64串
    // 删除 \r\n
    pngBase64 = pngBase64.replaceAll("\n", "").replaceAll("\r", "");

    Map map = new HashMap<>();
    map.put("base64", "data:image/png;base64," + pngBase64);
    //            map.put("validateCode", vCode.createImage()[0]);

    return Result.success(map);
  }
}
