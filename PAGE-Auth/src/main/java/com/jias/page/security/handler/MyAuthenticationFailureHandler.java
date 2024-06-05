package com.jias.page.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception
    ) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Result result = Result.failure(ResultEnum.SIGN_IN_FAILURE);
        Result result = Result.failure(exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
