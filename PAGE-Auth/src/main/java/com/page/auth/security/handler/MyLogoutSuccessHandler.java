package com.page.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.page.common.configuration.JwtConfiguration;
import com.page.common.enums.ResultEnum;
import com.page.common.utils.jwtUtil.JwtUtil;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    JwtConfiguration jwtConfiguration;

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException {
        String authHeader = request.getHeader(jwtConfiguration.getAuthHeaderKey());
        String authToken = authHeader.substring(jwtConfiguration.getTokenPrefix().length());
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.success(ResultEnum.SIGN_OUT_SUCCESS);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
