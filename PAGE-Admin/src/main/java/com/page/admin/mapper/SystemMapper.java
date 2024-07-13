package com.page.admin.mapper;

import com.page.admin.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMapper {
  /**
   * 根据角色获取用户路由
   *
   * @param
   * @return
   */
  List<Menu> getRouterListByRole();

  List<Menu> getMenuList();

  int addMenuInfo(Menu menu);

  int updateMenuInfo(Menu menu);

  int delMenuInfo(String mId);
}
