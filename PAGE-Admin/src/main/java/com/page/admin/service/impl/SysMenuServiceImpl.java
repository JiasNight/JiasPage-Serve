package com.page.admin.service.impl;

import com.page.admin.domain.vo.MenuMeta;
import com.page.admin.domain.vo.MenuVO;
import com.page.admin.mapper.SysMenuMapper;
import com.page.admin.service.ISysMenuService;
import com.page.common.domain.entity.SysMenu;
import com.page.common.utils.resultUtil.Result;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService {

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
  public Result getMenuList() {
    try {
      List<SysMenu> mList = sysMenuMapper.getMenuList();
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
  public Result addMenuInfo(MenuVO menuInfo) {
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
      int i = sysMenuMapper.addMenuInfo(menu);
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
  public Result updateMenuInfo(MenuVO menuInfo) {
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
      int i = sysMenuMapper.updateMenuInfo(sysMenu);
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
      int i = sysMenuMapper.delMenuInfo(mId);
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
  public List<MenuVO> buildMenusTree(List<SysMenu> list) {
    List<MenuVO> treeList = new ArrayList();
    List<MenuVO> copyList = new ArrayList<MenuVO>();
    for (int i = 0; i < list.size(); i++) {
      SysMenu menu = list.get(i);
      MenuVO menuVo = new MenuVO();
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
