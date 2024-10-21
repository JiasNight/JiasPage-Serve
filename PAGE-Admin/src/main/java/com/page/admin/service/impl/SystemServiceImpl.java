package com.page.admin.service.impl;

import com.page.admin.domain.vo.MenuMeta;
import com.page.admin.domain.vo.MenuVO;
import com.page.admin.mapper.SysMenuMapper;
import com.page.admin.mapper.SystemMapper;
import com.page.admin.service.ISystemService;
import com.page.common.domain.entity.SysMenu;
import com.page.common.utils.resultUtil.Result;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements ISystemService {

  @Autowired SystemMapper systemMapper;

  @Autowired SysMenuMapper sysMenuMapper;

  @Override
  public Result getRouterList() {
    try {
      List<SysMenu> mList = sysMenuMapper.getRouterListByRole();
      // lambda表达式实现List接口sort方法排序
      mList.sort(
          (m1, m2) -> {
            return m1.getOrder() - m2.getOrder();
          });
      List<MenuVO> menusTree = buildMenusTree(mList);
      return Result.success(menusTree);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result getRegionsById() {
    return null;
  }

  /**
   * 组装出菜单树结构
   *
   * @param list
   * @return
   */
  public List<MenuVO> buildMenusTree(List<SysMenu> list) {
    List<MenuVO> treeList = new ArrayList();
    List<MenuVO> copyList = new ArrayList<MenuVO>();
    for (int i = 0; i < list.size(); i++) {
      SysMenu sysMenu = list.get(i);
      MenuVO menuVo = new MenuVO();
      MenuMeta menuMeta = new MenuMeta();
      menuMeta.setTitle(sysMenu.getTitle());
      menuMeta.setIcon(sysMenu.getIcon());
      menuMeta.setType(sysMenu.getType());
      menuMeta.setCache(sysMenu.getCache());
      menuMeta.setDisabled(sysMenu.getDisabled());
      menuMeta.setShow(sysMenu.getShow());
      menuMeta.setDescription(sysMenu.getDescription());
      menuVo.setId(sysMenu.getId());
      menuVo.setPid(sysMenu.getPid());
      menuVo.setName(sysMenu.getName());
      menuVo.setPath(sysMenu.getPath());
      menuVo.setMeta(menuMeta);
      menuVo.setComponent(sysMenu.getComponent());
      menuVo.setOrder(sysMenu.getOrder());
      menuVo.setCreateBy(sysMenu.getCreateBy());
      menuVo.setCreateTime(sysMenu.getCreateTime());
      menuVo.setUpdateTime(sysMenu.getUpdateTime());
      copyList.add(menuVo);
    }

    for (MenuVO menuVo : copyList) {
      if (menuVo.getPid().equals("0")) {
        findChildrenMenu(menuVo, copyList);
        treeList.add(menuVo);
      }
    }
    return treeList;
  }

  public void findChildrenMenu(MenuVO rootMenu, List<MenuVO> list) {
    List<MenuVO> childList = new ArrayList();
    //	遍历所有数据，找到是入参父节点的子节点的数据，然后加到childList集合中。
    for (MenuVO cMenu : list) {
      if (rootMenu.getId().equals(cMenu.getPid())) childList.add(cMenu);
    }
    //	若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
    if (childList.size() == 0) return;
    // 设置父节点的子节点列表
    rootMenu.setChildren(childList);
    // 若子节点存在，接着递归调用该方法，寻找子节点的子节点。
    for (MenuVO child : childList) {
      findChildrenMenu(child, list);
    }
  }
}
