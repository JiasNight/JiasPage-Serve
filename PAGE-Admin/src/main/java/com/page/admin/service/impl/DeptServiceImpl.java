package com.page.admin.service.impl;

import com.page.admin.domain.entity.Dept;
import com.page.admin.domain.vo.DeptVo;
import com.page.admin.mapper.DeptMapper;
import com.page.admin.service.IDeptService;
import com.page.auth.domain.SysUser;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

  @Resource DeptMapper deptMapper;

  @Override
  public Result getDeptData() {
    List<Dept> deptList;
    try {
      deptList = deptMapper.getDeptData();
      // lambda表达式实现List接口sort方法排序
      deptList.sort(
          (m1, m2) -> {
            return m1.getOrder() - m2.getOrder();
          });
      List<DeptVo> deptTree = buildDeptTree(deptList);
      return Result.success(deptTree);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result addDeptInfo(Dept dept) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      dept.setId(UUID.randomUUID().toString());
      dept.setCreateBy(sysUser.getUsername());
      dept.setCreateTime(time);
      dept.setUpdateTime(time);
      dept.setUpdateBy(sysUser.getUsername());
      dept.setIsDeleted(0);
      int i = deptMapper.addDeptInfo(dept);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result updateDeptInfo(Dept dept) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      dept.setUpdateTime(time);
      dept.setUpdateBy(sysUser.getUsername());
      int i = deptMapper.updateDeptInfo(dept);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result delDeptInfo(String dId) {
    try {
      int i = deptMapper.delDeptInfo(dId);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  /**
   * 组装出部门树结构
   *
   * @param list
   * @return
   */
  public List<DeptVo> buildDeptTree(List<Dept> list) {
    List<DeptVo> treeList = new ArrayList();
    List<DeptVo> copyList = new ArrayList<DeptVo>();
    for (int i = 0; i < list.size(); i++) {
      Dept dept = list.get(i);
      DeptVo deptVo = new DeptVo();
      deptVo.setId(dept.getId());
      deptVo.setPid(dept.getPid());
      deptVo.setName(dept.getName());
      deptVo.setCode(dept.getCode());
      deptVo.setDescription(dept.getDescription());
      deptVo.setOrder(dept.getOrder());
      deptVo.setStatus(dept.getStatus());
      deptVo.setCreateBy(dept.getCreateBy());
      deptVo.setCreateTime(dept.getCreateTime());
      deptVo.setUpdateBy(dept.getUpdateBy());
      deptVo.setUpdateTime(dept.getUpdateTime());
      copyList.add(deptVo);
    }

    for (DeptVo deptVo : copyList) {
      if (deptVo.getPid().equals("0")) {
        findChildrenDept(deptVo, copyList);
        treeList.add(deptVo);
      }
    }
    return treeList;
  }

  public void findChildrenDept(DeptVo rootDept, List<DeptVo> list) {
    List<DeptVo> childList = new ArrayList();
    //	遍历所有数据，找到是入参父节点的子节点的数据，然后加到childList集合中。
    for (DeptVo cDept : list) {
      if (rootDept.getId().equals(cDept.getPid())) childList.add(cDept);
    }
    //	若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
    if (childList.size() == 0) return;
    // 设置父节点的子节点列表
    rootDept.setChildren(childList);
    // 若子节点存在，接着递归调用该方法，寻找子节点的子节点。
    for (DeptVo child : childList) {
      findChildrenDept(child, list);
    }
  }
}
