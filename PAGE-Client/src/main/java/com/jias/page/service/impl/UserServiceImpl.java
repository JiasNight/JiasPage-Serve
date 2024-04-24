package com.jias.page.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jias.page.mapper.UserMapper;
import com.jias.page.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

/**
 * @author JIAS
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    /**
     * 获取用户信息列表
     * @param page 分页
     * @return 用户信息列表
     */
    @Override
    public IPage<User> getUserList(Page<User> page) {
        try {
            IPage<User> userList = userMapper.selectUserList(page);
            return userList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 添加用户信息
     * @param user 用户实体
     * @return boolean
     */
    @Override
    public boolean addUserInfo(User user) {
        try {
            user.setUserId(UUID.randomUUID().toString());
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            user.setCreateTime(time);
            user.setUpdateTime(time);
            user.setIsDeleted(0);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String userPassword = user.getUserPassword();
            String encode = bCryptPasswordEncoder.encode(userPassword);
            user.setUserPassword(encode);
            int i = userMapper.addUserInfo(user);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除用户
     * @param userId 用户id
     * @return boolean
     */
    @Override
    public boolean delUserInfo(String userId) {
        try {
            User user = new User();
            user.setUserId(userId);
            user.setIsDeleted(1);
            user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
            int i = userMapper.delUserInfo(user);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改用户
     * @param user 用户实体
     * @return boolean
     */
    @Override
    public boolean updateUserInfo(User user) {
        try {
            user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
            int i = userMapper.updateUserInfo(user);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public Map selectUserInfoById(String userId) {
        Map map;
        try {
            map = userMapper.selectUserInfoById(userId);
            return map;
        } catch (Exception e) {
            return map = null;
        }
    }
}
