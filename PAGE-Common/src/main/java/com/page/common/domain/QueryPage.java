package com.page.common.domain;

import java.io.Serializable;

public class QueryPage<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 页码 */
  private Integer pageNum = 1;

  /** 页大小 */
  private Integer pageSize = 10;

  /** 排序字段 */
  private String sortBy = "createTime";

  /** 是否升序 */
  private Boolean isAsc;

  /** 查询条件 */
  private T query;

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public Boolean getAsc() {
    return isAsc;
  }

  public void setAsc(Boolean asc) {
    isAsc = asc;
  }

  public T getQuery() {
    return query;
  }

  public void setQuery(T query) {
    this.query = query;
  }
}
