package com.page.admin.controller;

import com.page.admin.domain.entity.Dept;
import com.page.admin.domain.entity.Role;
import com.page.admin.service.IDeptService;
import com.page.admin.service.IRoleService;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/dept")
public class DeptController {

  @Resource
  IDeptService deptService;

  @GetMapping("/list")
  public Result getDeptData() {
      return deptService.getDeptData();
  }

  @PostMapping("/add")
  public Result addDept(@RequestBody Dept dept) {
      return deptService.addDeptInfo(dept);
  }

  @PostMapping("/update")
  public Result updateDept(@RequestBody Dept dept) {
    return deptService.updateDeptInfo(dept);
  }

  @GetMapping("/delete/{dId}")
  public Result delDept(@PathVariable("dId") String dId) {
    return deptService.delDeptInfo(dId);
  }
}
