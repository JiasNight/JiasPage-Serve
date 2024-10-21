package com.page.admin.controller;

import com.page.admin.service.ISysDeptService;
import com.page.common.domain.entity.SysDept;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/dept")
public class SysDeptController {

  @Resource ISysDeptService deptService;

  @GetMapping("/list")
  public Result getDeptData() {
    return deptService.getDeptData();
  }

  @PostMapping("/add")
  public Result addDept(@RequestBody SysDept sysDept) {
    return deptService.addDeptInfo(sysDept);
  }

  @PostMapping("/update")
  public Result updateDept(@RequestBody SysDept sysDept) {
    return deptService.updateDeptInfo(sysDept);
  }

  @GetMapping("/delete/{dId}")
  public Result delDept(@PathVariable("dId") String dId) {
    return deptService.delDeptInfo(dId);
  }
}
