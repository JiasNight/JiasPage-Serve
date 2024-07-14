package com.page.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfiguration {

  // 签发主体
  private String issuer = "page";
  // 请求头
  private String authHeaderKey = "Authorization";
  // token开始字符
  private String tokenPrefix = "Bearer";
  // 密钥
  private String secret = "page";
  // 过期时间,默认1小时，单位毫秒
  private long expiresTime = 360000;

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getAuthHeaderKey() {
    return authHeaderKey;
  }

  public void setAuthHeaderKey(String authHeaderKey) {
    this.authHeaderKey = authHeaderKey;
  }

  public String getTokenPrefix() {
    return tokenPrefix + " ";
  }

  public void setTokenPrefix(String tokenPrefix) {
    this.tokenPrefix = tokenPrefix;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public long getExpiresTime() {
    return expiresTime;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }
}
