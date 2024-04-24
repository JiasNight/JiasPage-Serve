package com.jias.page.mapper;

import com.jias.page.domain.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
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
