package com.page.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.page.auth.domain.MyUserDetails;
import com.page.common.configuration.JwtConfiguration;
import com.page.common.utils.jwtUtil.JwtUtil;
import com.page.common.configuration.RedisConfiguration;
import com.page.common.utils.resultUtil.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Resource
  JwtUtil jwtUtil;

  @Resource
  RedisConfiguration redisConfiguration;

  @Resource
  JwtConfiguration jwtConfiguration;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    // 从认证对象中获取用户信息，并将其转换为 MyUserDetails 对象以便后续使用。(从loadUserByUsername方法返回的)
    MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
    Map<String, Object> userMap = new HashMap<String, Object>();
    userMap.put("userId", myUserDetails.getId());
    userMap.put("username", myUserDetails.getUsername());
    userMap.put("password", myUserDetails.getPassword());
    String token = jwtUtil.createJwt(myUserDetails.getId(), myUserDetails.getUsername(), userMap);
    // redisUtil.set(myUserDetails.getId(), token, 300);
    System.out.println("jwt时间为： " + jwtConfiguration.getExpiresTime());
    System.out.println("jwt密钥为： " + jwtConfiguration.getSecret());
    System.out.println("token开始字符为： " + jwtConfiguration.getTokenPrefix());
    System.out.println("AuthHeader为： " + jwtConfiguration.getAuthHeaderKey());
    Jedis jedis = new Jedis(redisConfiguration.getHost(), redisConfiguration.getPort());
    jedis.set("page_user_agent:" + myUserDetails.getId(), token);
    jedis.expire("page_user_agent:" + myUserDetails.getId(), jwtConfiguration.getExpiresTime() / 1000);
    jedis.close();
    ObjectMapper objectMapper = new ObjectMapper();
    Result result = Result.success(token);
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(result));
    response.getWriter().flush();
    response.getWriter().close();
  }
}
