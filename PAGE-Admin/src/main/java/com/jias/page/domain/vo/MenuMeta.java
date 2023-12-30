package com.jias.page.domain.vo;

public class MenuMeta {

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
  private int menuType;

  /** 菜单描述 */
  private String description;

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

  public int getMenuType() {
    return menuType;
  }

  public void setMenuType(int menuType) {
    this.menuType = menuType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
