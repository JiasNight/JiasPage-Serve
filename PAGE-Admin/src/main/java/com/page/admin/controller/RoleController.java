package com.page.admin.controller;

import com.page.admin.domain.entity.Role;
import com.page.admin.domain.vo.MenuVo;
import com.page.admin.service.IMenuService;
import com.page.admin.service.IRoleService;
import com.page.common.utils.resultUtil.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

  @Autowired
  IRoleService roleService;

  @GetMapping("/list")
  public Result getMenuList() {
      return roleService.getRoleList();
  }

  @PostMapping("/add")
  public Result addMenu(@RequestBody Role role) {
      return roleService.addRoleInfo(role);
  }

  @PostMapping("/update")
  public Result updateMenu(@RequestBody Role role) {
    return roleService.updateRoleInfo(role);
  }

  @GetMapping("/delete/{rId}")
  public Result delMenu(@PathVariable("rId") String rId) {
    return roleService.delRoleInfo(rId);
  }
}
