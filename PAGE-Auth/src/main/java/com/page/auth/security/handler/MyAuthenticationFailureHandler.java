package com.page.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.page.common.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
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
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    String str = null;
    if (exception instanceof AccountExpiredException) {
      str = "账户过期，登录失败!";
    } else if (exception instanceof BadCredentialsException) {
      str = "用户名或密码错误，登录失败!";
    } else if (exception instanceof CredentialsExpiredException) {
      str = "密码过期，登录失败!";
    } else if (exception instanceof DisabledException) {
      str = "账户被禁用，登录失败!";
    } else if (exception instanceof LockedException) {
      str = "账户被锁，登录失败!";
    } else if (exception instanceof InternalAuthenticationServiceException) {
      str = "账户不存在，登录失败!";
    } else {
      str = exception.getMessage();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    Result result = Result.failure(str);
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(result));
    response.getWriter().flush();
    response.getWriter().close();
  }
}
