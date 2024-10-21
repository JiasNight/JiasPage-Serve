package com.page.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.page.common.domain.entity.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
  /**
   * 删除用户和角色的关系
   *
   * @param userId 用户id
   * @return 结果
   */
  public int deleteRolesByUserId(String userId);

  /**
   * 批量新增用户角色信息
   *
   * @param userRoleList 用户角色列表
   * @return 结果
   */
  public int batchInsertUserRole(List<SysUserRole> userRoleList);

  /**
   * 根据用户id查询用户和角色的关系列表
   *
   * @param userId
   * @return
   */
  public List<SysUserRole> getUserRoleListByUserId(String userId);
}
