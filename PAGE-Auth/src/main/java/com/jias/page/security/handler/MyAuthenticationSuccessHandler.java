package com.jias.page.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.domain.vo.UserInfo;
import com.jias.page.enums.ResultEnum;
import com.jias.page.service.IUserService;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    IUserService userService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        // 从认证对象中获取用户信息，并将其转换为 User(userdetails) 对象以便后续使用。(从loadUserByUsername方法返回的)
        User user = (User) authentication.getPrincipal();
        UserInfo userInfo = userService.getUserByUsername(user.getUsername());
//        MyUserDetail user = (MyUserDetail) authentication.getPrincipal();
//        //  获取随机token 并存到Redis中
//        String token = UUID.randomUUID().toString().replaceAll("-", "");
//        LocalCache.getInstance().putValue(token, objectMapper.writeValueAsString(user), 60 * 60);
//        UserVO userVO = new UserVO();
//        userVO.setUserName(user.getUserName())
//                .setUserErrCount("0")
//                .setUserLastErrTime(null);
//        userService.updateUserErrCount(userVO);
//
//        LogVO logVO = new LogVO();
//        logVO.setLogOperateUser(user.getUserName())
//                .setLogContent("登录成功")
//                .setLogType("登录日志");
//        logService.addLog(logVO);

        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
