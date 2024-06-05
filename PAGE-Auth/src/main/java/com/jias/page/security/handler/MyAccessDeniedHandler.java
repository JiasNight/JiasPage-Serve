package com.jias.page.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Result result = Result.failure(ResultEnum.NO_PERMISSION);
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(result));
    response.getWriter().flush();
    response.getWriter().close();
  }
}
