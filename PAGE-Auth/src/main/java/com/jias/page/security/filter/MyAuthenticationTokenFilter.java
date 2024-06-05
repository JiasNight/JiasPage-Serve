package com.jias.page.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.jwtUtil.JWTUtil;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
public class MyAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        //  从header中获取token
        String authHeader = request.getHeader(JWTUtil.AUTH_HEADER_KEY);
        if (ObjectUtils.isEmpty(authHeader)) {
            // 没有携带 token 则 放行
            filterChain.doFilter(request, response);
            return;
        }
        this.doParse(request, response, filterChain, authHeader);
    }

    private void doParse(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String authHeader
    ) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        try{
            //  如果认证码以规定值开头
            if (authHeader.startsWith(JWTUtil.TOKEN_PREFIX)) {
                // 提取token值
                String token = authHeader.substring(JWTUtil.TOKEN_PREFIX.length());
                if (ObjectUtils.isEmpty(token)) {
                    Result result = Result.failure(ResultEnum.PERMISSION_TOKEN_EXPIRED);
                    response.getWriter().write(objectMapper.writeValueAsString(result));
                } else {
                    // 检查 token 是否过期
                    boolean expiration = JWTUtil.isExpiration(token);
                    if (expiration) {
                        Result result = Result.failure(ResultEnum.PERMISSION_TOKEN_EXPIRED);
                        response.getWriter().write(objectMapper.writeValueAsString(result));
                    }
                }
            }
            // 放行
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            Result result = Result.failure(ResultEnum.PERMISSION_TOKEN_INVALID);
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }
}
