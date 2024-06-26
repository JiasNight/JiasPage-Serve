package com.jias.page.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import com.jias.page.config.TransferConfig;
import com.jias.page.domain.SignInUser;
import com.jias.page.domain.vo.UserInfo;
import com.jias.page.service.ISysUserService;
import com.jias.page.utils.redisUtil.RedisConfigProperties;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Tag(name = "登陆验证")
@RequestMapping("/user")
public class SysUserController {

  @Autowired ISysUserService sysUserService;

  @Autowired TransferConfig transferConfig;

  @Autowired RedisUtil redisUtil;

  @Autowired RedisConfigProperties redisConfigProperties;

  @Operation(summary = "用户登陆")
  @PostMapping("/signIn")
  public Result singIn(@RequestBody SignInUser signInUser) {

    String token = sysUserService.userSigIn(signInUser);
    return Result.success(token);
    // if (resultMap.get("isSignIn").equals("true")) {
    //   Map<String, String> tMap = new HashMap<String, String>();
    //   tMap.put("token", resultMap.get("token").toString());
    //   return Result.success(tMap);
    // } else {
    //   return Result.failure(
    //       ResultEnumUtil.getByCode((Integer) resultMap.get("code"), ResultEnum.class));
    // }
  }

  @Operation(summary = "获取验证码")
  @GetMapping("/validateCode")
  public Result getCode() throws Exception {
    //    // 禁止图像缓存。
    //    response.setHeader("Pragma", "no-cache");
    //    response.setHeader("Cache-Control", "no-cache");
    //    response.setDateHeader("Expires", 0);
    //    response.setContentType("image/jpeg");
    //    String ip = request.getRemoteAddr();
    //
    //
    //
    //    System.out.println("当前IP:" + ip);
    //    ValidateCode vCode = new ValidateCode();
    //    // 删除以前的
    //    session.removeAttribute("imageCode");
    //    // 将验证码存入Session
    //    session.setAttribute("imageCode", vCode.createImage()[0]);
    //
    //    // 将图片转正base64
    //    BufferedImage image = (BufferedImage) vCode.createImage()[1];
    //    // 转base64
    //    Base64.Encoder encoder = Base64.getEncoder();
    //    // io流
    //    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //    // 写入流中
    //    ImageIO.write(image, "png", baos);
    //    // 转换成字节
    //    byte[] bytes = baos.toByteArray();
    //    // 转换成base64串
    //    String pngBase64 = encoder.encodeToString(bytes).trim();
    //    // 删除 \r\n
    //    pngBase64 = pngBase64.replaceAll("\n", "").replaceAll("\r", "");
    //
    //    Map map = new HashMap<>();
    //    map.put("base64", "data:image/png;base64," + pngBase64);
    //    map.put("safe", transferConfig.getRequestSafe());

    LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
    String verifyId = IdUtil.simpleUUID();
    // 图形验证码写出，可以写出到文件，也可以写出到流
    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
    lineCaptcha.write(os);
    String code = lineCaptcha.getCode();
    // 缓存一分钟的验证码
    redisUtil.set(verifyId, code, 60);
    Jedis jedis = new Jedis(redisConfigProperties.getHost(), redisConfigProperties.getPort());
    jedis.set("page_verCode:" + verifyId, code);
    // ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(5);
    Map map = new HashMap<>();
    // 验证码对应的redis上的uuid
    map.put("uuid", verifyId);
    // 图片上的验证码
    map.put("code", code);
    // 将图片转换成输出流传到前端上
    map.put("base64", "data:image/png;base64," + Base64.encode(os.toByteArray()));
    map.put("safe", transferConfig.getRequestSafe());

    return Result.success(map);
  }

  @Operation(summary = "通过token获取用户信息")
  @GetMapping("/getUserInfo")
  public Result getUserInfo(@RequestParam("token") String token) {
    UserInfo userInfo = sysUserService.getUserInfo(token);
    return Result.success(userInfo);
  }
}
