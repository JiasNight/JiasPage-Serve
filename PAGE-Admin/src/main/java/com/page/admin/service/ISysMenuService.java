package com.page.admin.service;

import com.page.admin.domain.vo.MenuVO;
import com.page.common.utils.resultUtil.Result;

public interface ISysMenuService {

  /**
   * 获取菜单列表
   *
   * @param
   * @return
   */
  Result getRouterList();

  Result getMenuList();

  Result addMenuInfo(MenuVO menu);

  Result updateMenuInfo(MenuVO menu);

  Result delMenuInfo(String mId);
}
