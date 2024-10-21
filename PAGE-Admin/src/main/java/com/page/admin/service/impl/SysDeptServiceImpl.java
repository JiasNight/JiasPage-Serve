package com.page.admin.service.impl;

import com.page.admin.domain.vo.DeptVO;
import com.page.admin.mapper.SysDeptMapper;
import com.page.admin.service.ISysDeptService;
import com.page.common.domain.entity.SysDept;
import com.page.common.domain.entity.SysUser;
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
public class SysDeptServiceImpl implements ISysDeptService {

  @Resource SysDeptMapper sysDeptMapper;

  @Override
  public Result getDeptData() {
    List<SysDept> deptList;
    try {
      deptList = sysDeptMapper.getDeptData();
      // lambda表达式实现List接口sort方法排序
      deptList.sort(
          (m1, m2) -> {
            return m1.getOrder() - m2.getOrder();
          });
      List<DeptVO> deptTree = buildDeptTree(deptList);
      return Result.success(deptTree);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result addDeptInfo(SysDept sysDept) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      // sysDept.setId(UUID.randomUUID().toString());
      sysDept.setCreateBy(sysUser.getUsername());
      sysDept.setCreateTime(time);
      sysDept.setUpdateTime(time);
      sysDept.setUpdateBy(sysUser.getUsername());
      sysDept.setIsDeleted(0);
      int i = sysDeptMapper.addDeptInfo(sysDept);
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
  public Result updateDeptInfo(SysDept sysDept) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SysUser sysUser = (SysUser) authentication.getPrincipal();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      sysDept.setUpdateTime(time);
      sysDept.setUpdateBy(sysUser.getUsername());
      int i = sysDeptMapper.updateDeptInfo(sysDept);
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
      int i = sysDeptMapper.delDeptInfo(dId);
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
  public List<DeptVO> buildDeptTree(List<SysDept> list) {
    List<DeptVO> treeList = new ArrayList();
    List<DeptVO> copyList = new ArrayList<DeptVO>();
    for (int i = 0; i < list.size(); i++) {
      SysDept sysDept = list.get(i);
      DeptVO deptVo = new DeptVO();
      deptVo.setId(sysDept.getId());
      deptVo.setPid(sysDept.getPid());
      deptVo.setName(sysDept.getName());
      deptVo.setCode(sysDept.getCode());
      deptVo.setDescription(sysDept.getDescription());
      deptVo.setOrder(sysDept.getOrder());
      deptVo.setStatus(sysDept.getStatus());
      deptVo.setCreateBy(sysDept.getCreateBy());
      deptVo.setCreateTime(sysDept.getCreateTime());
      deptVo.setUpdateBy(sysDept.getUpdateBy());
      deptVo.setUpdateTime(sysDept.getUpdateTime());
      copyList.add(deptVo);
    }

    for (DeptVO deptVo : copyList) {
      if (deptVo.getPid().equals("0")) {
        findChildrenDept(deptVo, copyList);
        treeList.add(deptVo);
      }
    }
    return treeList;
  }

  public void findChildrenDept(DeptVO rootDept, List<DeptVO> list) {
    List<DeptVO> childList = new ArrayList();
    //	遍历所有数据，找到是入参父节点的子节点的数据，然后加到childList集合中。
    for (DeptVO cDept : list) {
      if (rootDept.getId().equals(cDept.getPid())) childList.add(cDept);
    }
    //	若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
    if (childList.size() == 0) return;
    // 设置父节点的子节点列表
    rootDept.setChildren(childList);
    // 若子节点存在，接着递归调用该方法，寻找子节点的子节点。
    for (DeptVO child : childList) {
      findChildrenDept(child, list);
    }
  }
}
