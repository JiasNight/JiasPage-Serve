package com.page.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import com.page.common.configuration.TransferConfiguration;
import com.page.common.utils.cryptionUtil.RsaUtil;
import com.page.common.configuration.RedisConfiguration;
import com.page.common.utils.resultUtil.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@CrossOrigin
@RestController
@Tag(name = "security")
@RequestMapping("/security")
public class SecurityController {
  
  @Resource
  TransferConfiguration transferConfiguration;

  @Resource
  RedisConfiguration redisConfiguration;

  @Operation(summary = "获取公钥")
  @GetMapping("/pKey")
  public Result getPublicKey() throws Exception {
    Map<String, String> RSAMap = RsaUtil.genKeyPair();
    Map<String, String> resultMap = new HashMap<>();
    resultMap.put("pKey", RSAMap.get("publicKey"));
    return Result.success(resultMap);
  }

  @Operation(summary = "获取验证码")
  @GetMapping("/validateCode")
  public Result getCode() throws Exception {
    // 定义图形验证码的长、宽、验证码字符数、干扰线宽度
    ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(100, 40, 5, 3);
    String verifyId = IdUtil.simpleUUID();
    // 图形验证码写出，可以写出到文件，也可以写出到流
    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
    shearCaptcha.write(os);
    String code = shearCaptcha.getCode();
    Jedis jedis = new Jedis(redisConfiguration.getHost(), redisConfiguration.getPort());
    jedis.set("page_verify_code:" + code, code);
    jedis.expire("page_verify_code:" + code, 30);
    jedis.close();
    Map map = new HashMap<>();
    // 将图片转换成输出流传到前端上
    map.put("base64", "data:image/png;base64," + shearCaptcha.getImageBase64());
    map.put("safe", transferConfiguration.getRequestSafe());

    return Result.success(map);
  }
}
