package com.page.admin.service;

import com.page.admin.domain.vo.MenuVo;
import com.page.common.utils.resultUtil.Result;

import java.util.List;

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
