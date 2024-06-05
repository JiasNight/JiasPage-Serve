package com.jias.page.security.provider;

import com.jias.page.security.MyPasswordEncoder;
import com.jias.page.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author JSON
 * @date 2024/4/29
 * @description 自定义认证
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    MyPasswordEncoder myPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        try{
            boolean matches = myPasswordEncoder.matches(password, userDetails.getPassword());
            if(!matches){
                throw new AuthenticationException("用户名或密码错误！"){};
            }
        } catch (Exception e) {
            throw new AuthenticationException("用户名或密码错误！"){};
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
