package com.page.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.page.admin.domain.User;
import com.page.admin.domain.vo.UserPageVo;
import com.page.common.utils.resultUtil.Result;

import java.util.Map;

public interface IUserService {

    /**
     * 获取用户列表
     * @param page 分页
     * @return 分页列表
     */
    Result getUserList(UserPageVo userPageVo);

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
    Result delUserInfo(String userId);

    /**
     * 修改用户
     * @param user 用户实体
     * @return boolean
     */
    Result updateUserInfo(User user);

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return map
     */
    Result selectUserInfoById(String userId);
}
