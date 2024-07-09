package com.page.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "net-transfer")
public class TransferConfiguration {

  private Boolean requestSafe = false;

  private Boolean responseSafe = false;

  private String aesKey = "";

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
