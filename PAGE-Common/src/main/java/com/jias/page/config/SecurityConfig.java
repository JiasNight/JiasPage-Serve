package com.jias.page.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //放行权限
        http.authorizeRequests()
//                // 允许登录页面匿名访问
//                .antMatchers("/signIn", "/errPage").anonymous()
//                // 所有的静态资源允许匿名访问
//                .antMatchers(
//                        "/css/**",
//                        "/js/**",
//                        "/images/**",
//                        "/fonts/**",
//                        "/favicon.ico"
//                ).anonymous()
//                // 其他所有的请求都需要登录认证
//                .anyRequest().authenticated()
                .antMatchers("/**").permitAll().and().csrf().disable();
        super.configure(http);
    }
}
