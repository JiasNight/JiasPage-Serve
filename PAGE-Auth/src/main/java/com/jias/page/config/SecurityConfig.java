package com.jias.page.config;

import com.jias.page.security.MyAuthenticationEntryPoint;
import com.jias.page.security.filter.MyAuthenticationTokenFilter;
import com.jias.page.security.handler.MyAccessDeniedHandler;
import com.jias.page.security.handler.MyAuthenticationFailureHandler;
import com.jias.page.security.handler.MyAuthenticationSuccessHandler;
import com.jias.page.security.handler.MyLogoutSuccessHandler;
import com.jias.page.security.provider.MyAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * 登录时需要调用AuthenticationManager.authenticate执行一次校验
   *
   * @param authenticationConfiguration
   * @return
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * 配置跨源访问(CORS)
   *
   * @return
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    config.addAllowedOrigin("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
    return source;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        // 禁用basic明文验证,因为我们传输数据用的是post，而且请求体是JSON
        .httpBasic(Customizer.withDefaults())
        // 基于 token ，不需要 csrf,前后端分离架构不需要csrf保护
        .csrf(csrf -> csrf.disable())
        // 配置了基于表单的身份验证
        .formLogin(
            formLogin ->
                // 指定登录接口
                formLogin
                    .loginProcessingUrl("/signIn")
                    // 登录成功处理器
                    .successHandler(new MyAuthenticationSuccessHandler())
                    // 登录失败处理器
                    .failureHandler(new MyAuthenticationFailureHandler())
                    .permitAll())
        // 配置了退出登录的行为
        .logout(logout -> logout.logoutSuccessHandler(new MyLogoutSuccessHandler()))
        // 配置了会话管理策略:无状态管理政策,即不会创建会话，每个请求都需要进行完整的身份验证。
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // 设置失败认证处理
        .exceptionHandling(
            exceptions ->
                exceptions
                    // 用于处理未经身份验证的用户尝试访问受保护资源的情况。
                    .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                    .accessDeniedHandler(new MyAccessDeniedHandler()))
        //  授权过滤器
        .authorizeHttpRequests(
            authorizeHttpRequest ->
                authorizeHttpRequest
                    //  允许所有 OPTIONS 请求
                    //  .requestMatchers(HttpMethod.OPTIONS,
                    // "/**").permitAll()
                    //  允许直接访问授权登录接口
                    //  .requestMatchers(HttpMethod.POST,
                    // "/web/authenticate").permitAll()
                    //  允许 SpringMVC 的默认错误地址匿名访问
                    //  .requestMatchers("/error").permitAll()
                    //  其他所有接口必须有Authority信息，Authority在登录成功后的UserDetailImpl对象中默认设置“ROLE_USER”
                    // .requestMatchers("/**").hasAnyAuthority("ROLE_USER")
                    //   .requestMatchers("/heartBeat/**",
                    // "/main/**").permitAll()
                    // 开放两个接口，一个注册，一个登录，其余均要身份认证
                    .requestMatchers(
                        "/doc.html",
                        "/webjars/**",
                        "/v3/**",
                        "/swagger-resources/**",
                        "/favicon.ico")
                    .permitAll()
                    .requestMatchers("/user/validateCode", "/security/pKey", "/user/signIn")
                    .permitAll()
                    //  允许任意请求被已登录用户访问，不检查Authority
                    .anyRequest()
                    .authenticated())
        .authenticationProvider(new MyAuthenticationProvider());

    // 加我们自定义的token过滤器，替代UsernamePasswordAuthenticationFilter
    httpSecurity.addFilterBefore(
        new MyAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    // 退出
    httpSecurity.logout(
        logout -> {
          logout.logoutUrl("/logout").logoutSuccessHandler(new MyLogoutSuccessHandler());
        });

    // 跨域
    httpSecurity.cors(
        cors -> {
          cors.configurationSource(corsConfigurationSource());
        });

    return httpSecurity.build();
  }
}
