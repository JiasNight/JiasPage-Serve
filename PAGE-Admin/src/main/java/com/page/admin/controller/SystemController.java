package com.page.admin.controller;

import com.page.admin.service.ISystemService;
import com.page.common.utils.cryptionUtil.AesUtil;
import com.page.common.utils.redisUtil.RedisUtil;
import com.page.common.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class SystemController {

  @Autowired private RedisUtil redisUtil;

  @Autowired
  ISystemService systemService;

  @GetMapping("/aesKey")
  public Result getCommonAesKey() {
    try {
      String secretKey = AesUtil.getSecretKey(16);
      System.out.println(secretKey);
      redisUtil.set("aesKey", secretKey);
      return Result.success(secretKey);
    } catch (Exception e) {
      return Result.failure("获取密钥失败！");
    }
  }

  @GetMapping("/routes")
  public Result getUserRouters() {
    return systemService.getRouterList();
  }

  @GetMapping("/regions")
  public Result getRegionsById() {
    return systemService.getRegionsById();
  }

}
