package com.page.admin.mapper;

import com.page.admin.domain.entity.Dept;
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
  List<Dept> getDeptData();

  int addDeptInfo(Dept dept);

  int updateDeptInfo(Dept dept);

  int delDeptInfo(String dId);
}
