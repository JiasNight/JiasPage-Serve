package com.jias.page.security;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户发起未登录的请求会被AuthorizationFilter拦截，并抛出AccessDeniedException异常。异常被AuthenticationEntryPoint
 * 处理，默认会触发重定向到登录页。Spring Security开放了配置，允许我们自定义AuthenticationEntryPoint。
 * 那么我们就通过自定义AuthenticationEntryPoint来取消重定向行为，将接口改为返回JSON信息。
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    ObjectMapper objectMapper = new ObjectMapper();
    Result result = Result.failure(ResultEnum.NO_SIGN_IN);
    // Result result = Result.failure(authException.getMessage());
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(result));
    response.getWriter().flush();
    response.getWriter().close();
  }
}
