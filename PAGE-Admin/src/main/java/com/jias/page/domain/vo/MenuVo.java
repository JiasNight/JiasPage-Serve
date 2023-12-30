package com.jias.page.domain.vo;

import java.util.List;

public class MenuVo {

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

  private List<MenuVo> children;

  private String createBy;

  private String createTime;

  private String updateTime;

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

  public List<MenuVo> getChildren() {
    return children;
  }

  public void setChildren(List<MenuVo> children) {
    this.children = children;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }
}
