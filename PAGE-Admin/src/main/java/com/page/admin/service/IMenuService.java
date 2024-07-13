package com.page.admin.service;

import com.page.admin.domain.vo.MenuVo;
import com.page.common.utils.resultUtil.Result;

import java.util.List;

public interface IMenuService {

  /**
   * 获取菜单列表
   *
   * @param
   * @return
   */
  Result getRouterList();

  Result getMenuList();

  Result addMenuInfo(MenuVo menu);

  Result updateMenuInfo(MenuVo menu);

  Result delMenuInfo(String mId);
}
