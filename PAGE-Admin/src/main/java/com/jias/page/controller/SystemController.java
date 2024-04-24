package com.jias.page.controller;

import com.jias.page.domain.Menu;
import com.jias.page.domain.vo.MenuVo;
import com.jias.page.service.ISystemService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {

  @Autowired private RedisUtil redisUtil;

  @Autowired ISystemService systemService;

  @GetMapping("/aesKey")
  public Result getCommonAesKey() {
    try {
      String secretKey = AESUtil.getSecretKey(16);
      System.out.println(secretKey);
      redisUtil.set("aesKey", secretKey);
      return Result.success(secretKey);
    } catch (Exception e) {
      return Result.failure("获取密钥失败！");
    }
  }

  @GetMapping("/routes")
  public Result getUserRouters() {
//    List<MenuVo> menuList = systemService.getRouterList();
    return Result.success(systemService.getRouterList());
  }

}
