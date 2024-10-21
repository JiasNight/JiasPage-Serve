package com.page.admin.controller;

import com.page.admin.domain.vo.MenuVO;
import com.page.admin.service.ISysMenuService;
import com.page.common.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

  @Autowired ISysMenuService menuService;

  @PostMapping("/list")
  public Result getMenuList() {
    return menuService.getMenuList();
  }

  @PostMapping("/add")
  public Result addMenu(@RequestBody MenuVO menu) {
    return menuService.addMenuInfo(menu);
  }

  @PostMapping("/update")
  public Result updateMenu(@RequestBody MenuVO menu) {
    return menuService.updateMenuInfo(menu);
  }

  @GetMapping("/delete/{mId}")
  public Result delMenu(@PathVariable("mId") String mId) {
    return menuService.delMenuInfo(mId);
  }
}
