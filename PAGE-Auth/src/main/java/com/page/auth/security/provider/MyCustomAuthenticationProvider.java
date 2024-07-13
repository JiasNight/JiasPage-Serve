package com.page.auth.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author JSON
 * @date 2024/4/29
 * @description 自定义认证
 */
@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {

  private UserDetailsService userDetailsService;

  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // 获取用户输入的用户名和密码
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    try {
      boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
      if (!matches) {
        throw new AuthenticationException("用户名或密码错误！") {};
      }
    } catch (Exception e) {
      throw new AuthenticationException("用户名或密码错误！") {};
    }
    return new UsernamePasswordAuthenticationToken(
        userDetails, userDetails.getPassword(), userDetails.getAuthorities());
  }

  /**
   * 注意这里的supports方法，是实现多种认证方式的关键，认证管理器AuthenticationManager会通过这个supports方法来判定当前需要使用哪一种认证方式
   *
   * @param authentication
   * @return
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
}
