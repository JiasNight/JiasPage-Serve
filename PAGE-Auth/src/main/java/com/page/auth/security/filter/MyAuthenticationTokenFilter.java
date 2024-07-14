package com.page.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.page.auth.domain.MyUserDetails;
import com.page.auth.domain.SysUser;
import com.page.common.configuration.JwtConfiguration;
import com.page.common.configuration.RedisConfiguration;
import com.page.common.enums.ResultEnum;
import com.page.common.exception.CustomException;
import com.page.common.utils.jwtUtil.JwtUtil;
import com.page.common.utils.resultUtil.Result;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import redis.clients.jedis.Jedis;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource RedisConfiguration redisConfiguration;

  @Resource JwtConfiguration jwtConfiguration;

  @Resource JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    //  从header中获取token
    String authHeader = request.getHeader(jwtConfiguration.getAuthHeaderKey());
    // String session_code = String.valueOf(request.getSession().getAttribute("session_code"));
    // System.out.println(session_code);
    // 如果没有token，跳过该过滤器
    if (ObjectUtils.isEmpty(authHeader)) {
      // 没有携带 token 则 放行
      filterChain.doFilter(request, response);
      return;
    }
    this.doParse(request, response, filterChain, authHeader);
  }

  private void doParse(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain,
      String authHeader)
      throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    response.setContentType("application/json;charset=UTF-8");
    try {
      //  如果认证码以规定值开头
      if (authHeader.startsWith(jwtConfiguration.getTokenPrefix())) {
        // 提取token值
        String token = authHeader.substring(jwtConfiguration.getTokenPrefix().length());
        if (ObjectUtils.isEmpty(token)) {
          Result result = Result.failure(401, ResultEnum.PERMISSION_TOKEN_EXPIRED.getMessage());
          response.getWriter().write(objectMapper.writeValueAsString(result));
        } else {
          // 检查 token 是否过期
          boolean expiration = jwtUtil.isExpiration(token);
          if (expiration) {
            Result result = Result.failure(401, ResultEnum.PERMISSION_TOKEN_EXPIRED.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(result));
          } else {
            Claims claims = jwtUtil.parseJwt(token);
            SysUser sysUser = new SysUser();
            sysUser.setUserId(claims.getId());
            sysUser.setUsername(claims.getSubject());
            // 判断token是否存在redis里
            Jedis jedis = new Jedis(redisConfiguration.getHost(), redisConfiguration.getPort());
            long exists = jedis.exists("page_user_agent:" + sysUser.getUserId(), token);
            if (exists == 1) {
              MyUserDetails myUserDetails = new MyUserDetails(sysUser);
              UsernamePasswordAuthenticationToken authRequest =
                  new UsernamePasswordAuthenticationToken(
                      sysUser, null, myUserDetails.getAuthorities());
              SecurityContextHolder.getContext().setAuthentication(authRequest);
              request.setAttribute("uid", claims.getId());
            } else {
              Result result = Result.failure(401, ResultEnum.PERMISSION_TOKEN_EXPIRED.getMessage());
              response.getWriter().write(objectMapper.writeValueAsString(result));
            }
          }
        }
      }
      // 放行
      filterChain.doFilter(request, response);
    } catch (CustomException e) {
      Result result = Result.failure(e.getCode(), e.getMessage());
      response.getWriter().write(objectMapper.writeValueAsString(result));
    } catch (Exception e) {
      Result result = Result.failure(e.getMessage());
      response.getWriter().write(objectMapper.writeValueAsString(result));
    }
  }

  public void validate(HttpServletRequest request) throws ServerException {
    // 请求中传来的验证码
    String code = request.getParameter("code");
    String sessionCode = request.getSession().getAttribute("session_code").toString();
    if (StringUtil.isNullOrEmpty(code)) {
      throw new ServerException("验证码不能为空！");
    }
    if (StringUtil.isNullOrEmpty(sessionCode)) {
      throw new ServerException("验证码已经失效！");
    }
    if (!sessionCode.equalsIgnoreCase(code)) {
      throw new ServerException("验证码输入错误！");
    }
  }
}
