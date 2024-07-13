package com.page.admin.controller;

import com.page.admin.domain.vo.MenuVo;
import com.page.admin.service.IMenuService;
import com.page.common.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

  @Autowired IMenuService menuService;

  @PostMapping("/list")
  public Result getMenuList() {
    return menuService.getMenuList();
  }

  @PostMapping("/add")
  public Result addMenu(@RequestBody MenuVo menu) {
    return menuService.addMenuInfo(menu);
  }

  @PostMapping("/update")
  public Result updateMenu(@RequestBody MenuVo menu) {
    return menuService.updateMenuInfo(menu);
  }

  @GetMapping("/delete/{mId}")
  public Result delMenu(@PathVariable("mId") String mId) {
    return menuService.delMenuInfo(mId);
  }
}
