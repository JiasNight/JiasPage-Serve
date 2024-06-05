package com.jias.page.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jias.page.domain.User;
import com.jias.page.utils.resultUtil.Result;

import java.util.Map;

public interface IUserService {

    /**
     * 获取用户列表
     * @param page 分页
     * @return 分页列表
     */
    IPage<User> getUserList(Page<User> page);

    /**
     * 添加用户
     * @param user 用户实体
     * @return boolean
     */
    Result addUserInfo(User user);

    /**
     * 删除用户
     * @param userId 用户id
     * @return boolean
     */
    boolean delUserInfo(String userId);

    /**
     * 修改用户
     * @param user 用户实体
     * @return boolean
     */
    boolean updateUserInfo(User user);

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return map
     */
    Map selectUserInfoById(String userId);
}
