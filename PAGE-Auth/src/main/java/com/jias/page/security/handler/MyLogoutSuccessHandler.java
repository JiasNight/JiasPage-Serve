package com.jias.page.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.jwtUtil.JWTUtil;
import com.jias.page.utils.resultUtil.Result;
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

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException {
        String authHeader = request.getHeader(JWTUtil.AUTH_HEADER_KEY);
        String authToken = authHeader.substring(JWTUtil.TOKEN_PREFIX.length());
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.success(ResultEnum.SIGN_OUT_SUCCESS);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
