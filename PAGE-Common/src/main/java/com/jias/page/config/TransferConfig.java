package com.jias.page.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "net-transfer")
public class TransferConfig {

  private Boolean requestSafe;

  private Boolean responseSafe;

  private String aesKey;

  public Boolean getRequestSafe() {
    return requestSafe;
  }

  public void setRequestSafe(Boolean requestSafe) {
    this.requestSafe = requestSafe;
  }

  public Boolean getResponseSafe() {
    return responseSafe;
  }

  public void setResponseSafe(Boolean responseSafe) {
    this.responseSafe = responseSafe;
  }

  public String getAesKey() {
    return aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }
}
