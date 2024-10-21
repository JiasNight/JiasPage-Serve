package com.page.admin.service.impl;

import com.page.admin.domain.vo.MenuMeta;
import com.page.admin.domain.vo.MenuVo;
import com.page.admin.mapper.MenuMapper;
import com.page.admin.service.IMenuService;
import com.page.common.domain.entity.SysMenu;
import com.page.common.utils.resultUtil.Result;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

  @Autowired MenuMapper menuMapper;

  @Override
  public Result getRouterList() {
    try {
      List<SysMenu> mList = menuMapper.getRouterListByRole();
      // lambda表达式实现List接口sort方法排序
      mList.sort(
          (m1, m2) -> {
            return m1.getOrder() - m2.getOrder();
          });
      List<MenuVo> menusTree = buildMenusTree(mList);
      return Result.success(menusTree);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result getMenuList() {
    try {
      List<SysMenu> mList = menuMapper.getMenuList();
      // lambda表达式实现List接口sort方法排序
      mList.sort(
          (m1, m2) -> {
            return m1.getOrder() - m2.getOrder();
          });
      List<MenuVo> menusTree = buildMenusTree(mList);
      return Result.success(menusTree);
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result addMenuInfo(MenuVo menuInfo) {
    try {
      SysMenu menu = new SysMenu();
      MenuMeta menuMeta = menuInfo.getMeta();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      menu.setPath(menuInfo.getPath());
      menu.setName(menuInfo.getName());
      menu.setPid(menuInfo.getPid());
      menu.setOrder(menuInfo.getOrder());
      menu.setComponent(menuInfo.getComponent());
      menu.setIcon(menuMeta.getIcon());
      menu.setTitle(menuMeta.getTitle());
      menu.setType(menuMeta.getType());
      menu.setShow(menuMeta.getShow());
      menu.setDisabled(menuMeta.getDisabled());
      menu.setCache(menuMeta.getCache());
      menu.setDescription(menuMeta.getDescription());
      menu.setDisabled(menuMeta.getDisabled());
      menu.setId(UUID.randomUUID().toString());
      menu.setCreateBy("admin");
      menu.setCreateTime(time);
      menu.setUpdateTime(time);
      menu.setIsDeleted(0);
      int i = menuMapper.addMenuInfo(menu);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result updateMenuInfo(MenuVo menuInfo) {
    try {
      SysMenu sysMenu = new SysMenu();
      MenuMeta menuMeta = menuInfo.getMeta();
      String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
      sysMenu.setId(menuInfo.getId());
      sysMenu.setPath(menuInfo.getPath());
      sysMenu.setName(menuInfo.getName());
      sysMenu.setPid(menuInfo.getPid());
      sysMenu.setOrder(menuInfo.getOrder());
      sysMenu.setComponent(menuInfo.getComponent());
      sysMenu.setIcon(menuMeta.getIcon());
      sysMenu.setTitle(menuMeta.getTitle());
      sysMenu.setType(menuMeta.getType());
      sysMenu.setShow(menuMeta.getShow());
      sysMenu.setDisabled(menuMeta.getDisabled());
      sysMenu.setCache(menuMeta.getCache());
      sysMenu.setDescription(menuMeta.getDescription());
      sysMenu.setDisabled(menuMeta.getDisabled());
      sysMenu.setUpdateTime(time);
      int i = menuMapper.updateMenuInfo(sysMenu);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  @Override
  public Result delMenuInfo(String mId) {
    try {
      int i = menuMapper.delMenuInfo(mId);
      if (i > 0) {
        return Result.success();
      } else {
        return Result.failure();
      }
    } catch (Exception e) {
      return Result.failure(e);
    }
  }

  /**
   * 组装出菜单树结构
   *
   * @param list
   * @return
   */
  public List<MenuVo> buildMenusTree(List<SysMenu> list) {
    List<MenuVo> treeList = new ArrayList();
    List<MenuVo> copyList = new ArrayList<MenuVo>();
    for (int i = 0; i < list.size(); i++) {
      SysMenu menu = list.get(i);
      MenuVo menuVo = new MenuVo();
      MenuMeta menuMeta = new MenuMeta();
      menuMeta.setTitle(menu.getTitle());
      menuMeta.setIcon(menu.getIcon());
      menuMeta.setType(menu.getType());
      menuMeta.setCache(menu.getCache());
      menuMeta.setDisabled(menu.getDisabled());
      menuMeta.setShow(menu.getShow());
      menuMeta.setDescription(menu.getDescription());
      menuVo.setId(menu.getId());
      menuVo.setPid(menu.getPid());
      menuVo.setName(menu.getName());
      menuVo.setPath(menu.getPath());
      menuVo.setMeta(menuMeta);
      menuVo.setComponent(menu.getComponent());
      menuVo.setOrder(menu.getOrder());
      menuVo.setCreateBy(menu.getCreateBy());
      menuVo.setCreateTime(menu.getCreateTime());
      menuVo.setUpdateBy(menu.getUpdateBy());
      menuVo.setUpdateTime(menu.getUpdateTime());
      copyList.add(menuVo);
    }

    for (MenuVo menuVo : copyList) {
      if (menuVo.getPid().equals("0")) {
        findChildrenMenu(menuVo, copyList);
        treeList.add(menuVo);
      }
    }
    return treeList;
  }

  public void findChildrenMenu(MenuVo rootMenu, List<MenuVo> list) {
    List<MenuVo> childList = new ArrayList();
    //	遍历所有数据，找到是入参父节点的子节点的数据，然后加到childList集合中。
    for (MenuVo cMenu : list) {
      if (rootMenu.getId().equals(cMenu.getPid())) childList.add(cMenu);
    }
    //	若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
    if (childList.size() == 0) return;
    // 设置父节点的子节点列表
    rootMenu.setChildren(childList);
    // 若子节点存在，接着递归调用该方法，寻找子节点的子节点。
    for (MenuVo child : childList) {
      findChildrenMenu(child, list);
    }
  }
}
