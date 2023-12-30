package com.jias.page.controller;

import com.jias.page.domain.Menu;
import com.jias.page.domain.vo.MenuVo;
import com.jias.page.service.ISystemService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "系统相关")
@RequestMapping("/system")
public class SystemController {

  @Autowired private RedisUtil redisUtil;

  @Autowired ISystemService systemService;

  @ApiOperation(value = "获取AES加密密钥", notes = "AES加密密钥")
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

  @ApiOperation(value = "获取用户的路由表", notes = "获取用户的路由表")
  @PostMapping("/routes")
  public Result getUserRouters() {
    try {
      List<MenuVo> menuList = systemService.getRouterList();
      return Result.success(menuList);
    } catch (Exception e) {
      return Result.failure("获取用户路由表失败！");
    }
  }

  @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
  @PostMapping("/menu/list")
  public Result getMenuList() {
    try {
      List<MenuVo> menuList = systemService.getMenuList();
      return Result.success(menuList);
    } catch (Exception e) {
      return Result.failure("获取菜单列表失败！");
    }
  }

  @ApiOperation(value = "新增菜单列表", notes = "新增菜单列表")
  @PostMapping("/menu/add")
  public Result addMenu(@RequestBody MenuVo menu) {
    try {
      boolean b = systemService.addMenuInfo(menu);
      if (b) {
        return Result.success();
      } else {
        return Result.failure("新增菜单列表失败！");
      }
    } catch (Exception e) {
      return Result.failure("新增菜单列表失败！");
    }
  }
}
