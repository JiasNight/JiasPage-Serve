package com.page.admin.domain.vo;

import com.page.common.domain.BaseEntity;

import java.util.List;

public class MenuVO extends BaseEntity {

  /** 菜单id,主键 */
  private String id;

  /** 菜单父级id */
  private String pid;

  /** 菜单地址 */
  private String path;

  /** 菜单代码 */
  private String name;

  /** 菜单内容 */
  private MenuMeta meta;

  private int order;

  /** 菜单组件路径 */
  private String component;

  private List<MenuVO> children;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MenuMeta getMeta() {
    return meta;
  }

  public void setMeta(MenuMeta meta) {
    this.meta = meta;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getOrder() {
    return order;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public List<MenuVO> getChildren() {
    return children;
  }

  public void setChildren(List<MenuVO> children) {
    this.children = children;
  }
}
