package com.jias.page.controller;

import com.jias.page.config.TransferConfig;
import com.jias.page.domain.SignInUser;
import com.jias.page.domain.vo.UserInfo;
import com.jias.page.service.ISignInService;
import com.jias.page.service.IUserService;
import com.jias.page.utils.resultUtil.Result;
import com.jias.page.utils.resultUtil.ResultEnum;
import com.jias.page.utils.resultUtil.ResultEnumUtil;
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

@CrossOrigin
@RestController
@Api(tags = "登陆验证")
@RequestMapping("/user")
public class SysUserController {

  @Autowired ISignInService signInService;

  @Autowired IUserService userService;

  @Autowired TransferConfig transferConfig;

  @ApiOperation(value = "用户登陆", notes = "用户登陆")
  @PostMapping("/signIn")
  public Result singIn(@RequestBody SignInUser signInUser) {
    Map<String, Object> resultMap = signInService.userIsSigIn(signInUser);
    if (resultMap.get("isSignIn").equals("true")) {
      Map<String, String> tMap = new HashMap<String, String>();
      tMap.put("token", resultMap.get("token").toString());
      return Result.success(tMap);
    } else {
      return Result.failure(
          ResultEnumUtil.getByCode((Integer) resultMap.get("code"), ResultEnum.class));
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
    map.put("safe", transferConfig.getRequestSafe());

    return Result.success(map);
  }

  @ApiOperation(value = "获取用户信息", notes = "用户信息")
  @GetMapping("/getInfo")
  public Result getUserInfo(@RequestParam("token") String token) {
    UserInfo userInfo = userService.getUserInfo(token);
    return Result.success(userInfo);
  }
}
