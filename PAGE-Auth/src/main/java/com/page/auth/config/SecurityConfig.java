package com.page.auth.config;

import com.page.auth.security.MyAuthenticationEntryPoint;
import com.page.auth.security.MyPasswordEncoder;
import com.page.auth.security.filter.MyAuthenticationTokenFilter;
import com.page.auth.security.handler.MyAuthenticationFailureHandler;
import com.page.auth.security.handler.MyAuthenticationSuccessHandler;
import com.page.auth.security.handler.MyLogoutSuccessHandler;
import com.page.auth.security.provider.MobilecodeAuthenticationProvider;
import com.page.auth.security.provider.MyCustomAuthenticationProvider;
import com.page.auth.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * anyRequest | 匹配所有请求路径 access | SpringEl表达式结果为true时可以访问 anonymous | 匿名可以访问 denyAll | 用户不能访问
   * fullyAuthenticated | 用户完全认证可以访问（非remember-me下自动登录） hasAnyAuthority | 如果有参数，参数表示权限，则其中任何一个权限可以访问
   * hasAnyRole | 如果有参数，参数表示角色，则其中任何一个角色可以访问 hasAuthority | 如果有参数，参数表示权限，则其权限可以访问 hasIpAddress |
   * 如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问 hasRole | 如果有参数，参数表示角色，则其角色可以访问 permitAll | 用户可以任意访问
   * rememberMe | 允许通过remember-me登录的用户访问 authenticated | 用户登录后可访问
   */
  @Autowired MyUserDetailsServiceImpl myUserDetailsService;

  @Autowired MyAuthenticationTokenFilter myAuthenticationTokenFilter;

  @Autowired MyLogoutSuccessHandler myLogoutSuccessHandler;

  @Autowired MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

  @Autowired MyAuthenticationFailureHandler myAuthenticationFailureHandler;

  @Autowired MyAuthenticationEntryPoint myAuthenticationEntryPoint;

  @Autowired MyPasswordEncoder myPasswordEncoder;

  @Bean
  public MobilecodeAuthenticationProvider mobilecodeAuthenticationProvider() {
    MobilecodeAuthenticationProvider mobilecodeAuthenticationProvider =
        new MobilecodeAuthenticationProvider();
    mobilecodeAuthenticationProvider.setUserDetailsService(myUserDetailsService);
    return mobilecodeAuthenticationProvider;
  }

  @Bean
  public MyCustomAuthenticationProvider myCustomAuthenticationProvider() {
    MyCustomAuthenticationProvider myCustomAuthenticationProvider =
        new MyCustomAuthenticationProvider();
    myCustomAuthenticationProvider.setPasswordEncoder(myPasswordEncoder);
    myCustomAuthenticationProvider.setUserDetailsService(myUserDetailsService);
    return myCustomAuthenticationProvider;
  }

  /**
   * 身份认证管理器，调用authenticate()方法完成认证
   *
   * @param authenticationConfiguration
   * @return
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
    authenticationProviders.add(mobilecodeAuthenticationProvider());
    authenticationProviders.add(myCustomAuthenticationProvider());
    ProviderManager providerManager = new ProviderManager(authenticationProviders);
    return providerManager;
  }

  // /**
  //  * @return 身份校验机制、身份验证提供程序
  //  */
  // @Bean
  // public AuthenticationProvider authenticationProvider() {
  //   // 创建一个用户认证提供者
  //   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
  //   // 设置用户相关信息，可以从数据库中读取、或者缓存、或者配置文件
  //   authProvider.setUserDetailsService(myUserDetailsService);
  //   // 设置加密机制，用于对用户进行身份验证
  //   authProvider.setPasswordEncoder(myPasswordEncoder);
  //   return authProvider;
  // }
  //
  // /**
  //  * 基于用户名和密码或使用用户名和密码进行身份验证
  //  *
  //  * @param authenticationConfiguration
  //  * @return
  //  * @throws Exception
  //  */
  // @Bean
  // public AuthenticationManager authenticationManager(
  //     AuthenticationConfiguration authenticationConfiguration) throws Exception {
  //   return authenticationConfiguration.getAuthenticationManager();
  // }

  /**
   * 配置跨源访问(CORS)
   *
   * @return
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
    return source;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        // 禁用basic明文验证,因为我们传输数据用的是post，而且请求体是JSON
        .httpBasic(Customizer.withDefaults())
        // 基于 token ，不需要 csrf,前后端分离架构不需要csrf保护
        .csrf(csrf -> csrf.disable());

    /**
     * 登录表单配置 loginPage:登录页面请求地址 loginProcessingUrl:登录接口 过滤器 successForwardUrl：登录成功响应地址
     * failureForwardUrl：登录失败响应地址
     */
    httpSecurity
        .formLogin(
            formLogin ->
                // 跳转到自定义的登录页面
                formLogin
                    .loginPage("/")
                    // 自定义表单用户名参数，默认是username
                    .usernameParameter("username")
                    // 自定义表单密码参数，默认是password
                    .passwordParameter("password")
                    // 指定登录接口
                    .loginProcessingUrl("/user/signIn")
                    // 登录成功处理器
                    .successHandler(myAuthenticationSuccessHandler)
                    // 登录失败处理器
                    .failureHandler(myAuthenticationFailureHandler)
                    .permitAll())
        // 配置退出登录的行为
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler));

    httpSecurity
        //  授权过滤器
        .authorizeHttpRequests(
            authorizeHttpRequest ->
                authorizeHttpRequest
                    .requestMatchers(
                        "/doc.html",
                        "/doc.html/**",
                        "/webjars/**",
                        "/v3/**",
                        "/swagger-resources/**",
                        "/favicon.ico")
                    .permitAll()
                    // 开放两个接口，一个注册，一个登录，其余均要身份认证
                    .requestMatchers("/security/validateCode", "/security/pKey", "/system/user/signUp")
                    .permitAll()
                    // 允许任意请求被已登录用户访问，不检查Authority
                    .anyRequest()
                    .authenticated())
        // 配置了会话管理策略:无状态管理政策,即不会创建会话，每个请求都需要进行完整的身份验证。
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // 设置失败认证处理
        .exceptionHandling(
            exceptions ->
                exceptions
                    // 用于处理未经身份验证的用户尝试访问受保护资源的情况。
                    // 如果是认证过程中出现的异常会被封装成AuthenticationException然后调用AuthenticationEntryPoint对象的方法去进行异常处理。
                    // 如果是授权过程中出现的异常会被封装成AccessDeniedException然后调用AccessDeniedHandler对象的方法去进行异常处理。
                    .authenticationEntryPoint(myAuthenticationEntryPoint))
        .authenticationManager(authenticationManager());

    // 加我们自定义的token过滤器，去过滤接口请
    httpSecurity.addFilterBefore(
        myAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    // 退出
    httpSecurity.logout(
        logout -> {
          logout.logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler);
        });

    // 跨域
    httpSecurity.cors(
        cors -> {
          cors.configurationSource(corsConfigurationSource());
        });

    return httpSecurity.build();
  }
}
