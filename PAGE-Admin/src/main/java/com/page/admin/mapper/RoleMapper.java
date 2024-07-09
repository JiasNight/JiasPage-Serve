package com.page.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.page.admin.domain.entity.Role;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
  /**
   * 根据角色获取用户路由
   *
   * @param
   * @return
   */
  List<Role> getRoleList();

  int addRoleInfo(Role role);

  int updateRoleInfo(Role role);

  int delRoleInfo(String rId);
}
