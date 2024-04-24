package com.jias.page.controller;

import com.jias.page.domain.vo.MenuVo;
import com.jias.page.service.IMenuService;
import com.jias.page.service.ISystemService;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

  @Autowired
  IMenuService menuService;

  @PostMapping("/list")
  public Result getMenuList() {
    try {
      List<MenuVo> menuList = menuService.getMenuList();
      return Result.success(menuList);
    } catch (Exception e) {
      return Result.failure("获取菜单列表失败！");
    }
  }

  @PostMapping("/add")
  public Result addMenu(@RequestBody MenuVo menu) {
    try {
      boolean b = menuService.addMenuInfo(menu);
      if (b) {
        return Result.success();
      } else {
        return Result.failure("新增菜单列表失败！");
      }
    } catch (Exception e) {
      return Result.failure("新增菜单列表失败！");
    }
  }

  @PostMapping("/update")
  public Result updateMenu(@RequestBody MenuVo menu) {
    try {
      boolean b = menuService.updateMenuInfo(menu);
      if (b) {
        return Result.success();
      } else {
        return Result.failure("更新菜单列表失败！");
      }
    } catch (Exception e) {
      return Result.failure("更新菜单列表失败！");
    }
  }

  @GetMapping("/delete/{mId}")
  public Result delMenu(@PathVariable("mId") String mId) {
    try {
      boolean b = menuService.delMenuInfo(mId);
      if (b) {
        return Result.success();
      } else {
        return Result.failure("删除菜单列表失败！");
      }
    } catch (Exception e) {
      return Result.failure("新增菜单列表失败！");
    }
  }
}
