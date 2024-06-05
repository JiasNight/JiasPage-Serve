package com.jias.page.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.domain.MyUserDetails;
import com.jias.page.domain.SysUser;
import com.jias.page.domain.vo.UserInfo;
import com.jias.page.enums.ResultEnum;
import com.jias.page.service.ISysUserService;
import com.jias.page.utils.jwtUtil.JWTUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedisUtil redisUtil;

    @Autowired
    JWTUtil jwtUtil;

    public MyAuthenticationSuccessHandler(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        // 从认证对象中获取用户信息，并将其转换为 MyUserDetails 对象以便后续使用。(从loadUserByUsername方法返回的)
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userId", myUserDetails.getId());
        userMap.put("username", myUserDetails.getUsername());
        userMap.put("password", myUserDetails.getPassword());
        String token =
                jwtUtil.createJwt(myUserDetails.getId(), myUserDetails.getUsername(), userMap);
    System.out.println(token);
        redisUtil.set(myUserDetails.getId(), token, 300);
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.success(token);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
