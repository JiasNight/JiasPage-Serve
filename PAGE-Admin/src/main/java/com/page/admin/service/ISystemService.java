package com.page.admin.service;

import com.page.common.utils.resultUtil.Result;

public interface ISystemService {

  /**
   * 获取菜单列表
   *
   * @param
   * @return
   */
  Result getRouterList();

  Result getRegionsById();
}
