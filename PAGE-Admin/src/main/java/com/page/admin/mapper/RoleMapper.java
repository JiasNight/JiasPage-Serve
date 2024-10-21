package com.page.admin.mapper;

import com.page.admin.domain.vo.RoleListVo;
import com.page.common.domain.entity.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
  /**
   * 根据角色获取用户路由
   *
   * @param
   * @return
   */
  List<RoleListVo> getRoleList();

  int addRoleInfo(SysRole sysRole);

  int updateRoleInfo(SysRole sysRole);

  int delRoleInfo(String rId);
}
