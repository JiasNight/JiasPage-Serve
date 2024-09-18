package com.page.admin.domain.dto;

import com.page.common.domain.QueryPage;

/**
 * @author JSON
 * @date 2024/4/18
 * @description
 */
public class RoleQueryDto extends QueryPage {

  private String name;

  private String code;

  private String createDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
}
