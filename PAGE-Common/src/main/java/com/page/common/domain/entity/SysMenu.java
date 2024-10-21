package com.page.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.page.common.domain.BaseEntity;

/**
 * @author JSON
 * @date 2024/10/21
 * @description
 */
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

  /** 菜单id,主键 */
  @TableId(value = "id", type = IdType.ASSIGN_UUID)
  private String id;

  /** 菜单父级id */
  @TableField("pid")
  private String pid;

  /** 菜单地址 */
  @TableField("path")
  private String path;

  /** 菜单代码 */
  @TableField("name")
  private String name;

  /** 菜单名称 */
  @TableField("title")
  private String title;

  /** 菜单图标 */
  @TableField("icon")
  private String icon;

  /** 是否显示，0显示，1不显示 */
  @TableField("show")
  private int show;

  /** 是否禁用，0禁用，1不禁用 */
  @TableField("disabled")
  private int disabled;

  /** 是否缓存，0缓存，1不缓存 */
  @TableField("cache")
  private int cache;

  /** 菜单类型，0目录，1菜单，2按钮，3外链 */
  @TableField("type")
  private int type;

  /** 菜单描述 */
  @TableField("description")
  private String description;

  /** 菜单组件路径 */
  @TableField("component")
  private String component;

  /** 菜单序号 */
  @TableField("order")
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

  public void setComponent(String component) {
    this.component = component;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
