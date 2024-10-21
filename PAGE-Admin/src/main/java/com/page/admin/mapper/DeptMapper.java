package com.page.admin.mapper;

import com.page.common.domain.entity.SysDept;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
  /**
   * 根据角色获取用户路由
   *
   * @param
   * @return
   */
  List<SysDept> getDeptData();

  int addDeptInfo(SysDept sysDept);

  int updateDeptInfo(SysDept sysDept);

  int delDeptInfo(String dId);
}
