package com.page.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.page.common.domain.BaseEntity;

@TableName("sys_menus")
public class Menu extends BaseEntity {

  /** 菜单id,主键 */
  private String id;

  /** 菜单父级id */
  private String pid;

  /** 菜单地址 */
  private String path;

  /** 菜单代码 */
  private String name;

  /** 菜单名称 */
  private String title;

  /** 菜单图标 */
  private String icon;

  /** 是否显示，0显示，1不显示 */
  private int show;

  /** 是否禁用，0禁用，1不禁用 */
  private int disabled;

  /** 是否缓存，0缓存，1不缓存 */
  private int cache;

  /** 菜单类型，0目录，1菜单，2按钮，3外链 */
  private int type;

  /** 菜单描述 */
  private String description;

  /** 菜单组件路径 */
  private String component;

  private int order;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public int getShow() {
    return show;
  }

  public void setShow(int show) {
    this.show = show;
  }

  public int getDisabled() {
    return disabled;
  }

  public void setDisabled(int disabled) {
    this.disabled = disabled;
  }

  public int getCache() {
    return cache;
  }

  public void setCache(int cache) {
    this.cache = cache;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getComponent() {
    return component;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public void setComponent(String component) {
    this.component = component;
  }

}
