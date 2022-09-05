package com.jias.page.service.impl;

import com.jias.page.domain.User;
import com.jias.page.mapper.LoginMapper;
import com.jias.page.service.ILoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

/**
 * @author JIAS
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    LoginMapper loginMapper;

    /**
     * 用户是否登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return boolean
     */
    @Override
    public boolean userIsSigIn(String userName, String password) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            Map userList = loginMapper.userIsSigIn(userName);
            boolean isSigIn = bCryptPasswordEncoder.matches(password, userList.get("userPassword").toString());
            return isSigIn;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 用户添加
     *
     * @param userName 用户名
     * @param password 密码
     * @return boolean
     */
    @Override
    public boolean userAdd(String userName, String password) {
        try {
            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setUserName(userName);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String userPassword = bCryptPasswordEncoder.encode(password);
            user.setUserPassword(userPassword);
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            user.setCreateTime(time);
            user.setUpdateTime(time);
            int isSuccess = loginMapper.userAdd(user);
            if (isSuccess > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
