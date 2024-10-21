package com.page.admin.mapper;

import com.page.common.domain.entity.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMenuMapper {
  /**
   * 根据角色获取用户路由
   *
   * @param
   * @return
   */
  List<SysMenu> getRouterListByRole();

  List<SysMenu> getMenuList();

  int addMenuInfo(SysMenu sysMenu);

  int updateMenuInfo(SysMenu sysMenu);

  int delMenuInfo(String mId);
}
