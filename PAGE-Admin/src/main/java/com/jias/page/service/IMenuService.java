package com.jias.page.service;

import com.jias.page.domain.vo.MenuVo;
import java.util.List;

public interface IMenuService {

  /**
   * 获取菜单列表
   *
   * @param
   * @return
   */
  List getRouterList();

  List getMenuList();

  boolean addMenuInfo(MenuVo menu);

  boolean updateMenuInfo(MenuVo menu);

  boolean delMenuInfo(String mId);
}
