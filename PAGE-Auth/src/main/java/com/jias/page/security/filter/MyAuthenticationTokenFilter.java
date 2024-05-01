package com.jias.page.security.filter;

import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.jwtUtil.JWTUtil;
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
        //  从header中获取验证信息
        String authHeader = request.getHeader(JWTUtil.AUTH_HEADER_KEY);
        if (ObjectUtils.isEmpty(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        this.doParse(request, response, filterChain, authHeader);
    }

    private void doParse(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String authHeader
    ) throws ServletException, IOException {
        try{
            //  如果认证码  以规定值开头
            if (authHeader.startsWith(JWTUtil.TOKEN_PREFIX)) {
                // 提取token值
                String token = authHeader.substring(JWTUtil.TOKEN_PREFIX.length());
                if (ObjectUtils.isEmpty(token)) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    JWTUtil.isExpiration(token);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(ResultEnum.PERMISSION_TOKEN_ILLEGAL.getMessage());
        }
    }
}
