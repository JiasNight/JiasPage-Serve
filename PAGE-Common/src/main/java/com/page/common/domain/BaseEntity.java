package com.page.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

/**
 * Entity基类
 *
 * @author JSON
 */
public class BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  /** 创建者 */
  private String createBy;

  /** 创建时间 */
  private String createTime;

  /** 更新者 */
  private String updateBy;

  /** 更新时间 */
  private String updateTime;

  /** 是否删除标志 默认为0，1已删除 */
  @TableLogic
  @TableField(value = "is_deleted")
  private int isDeleted;

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

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }
}
