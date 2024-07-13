package com.page.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

  // 设置 openapi 基础参数
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("SpringBoot API 管理")
                .contact(
                    new Contact().name("PAGE").email("xxxx@qq.com").url("https://www.jias.link"))
                .version("1.0")
                .description("SpringBoot 集成 Knife4j 示例")
                .license(new License().name("Apache 2.0")));
  }
}
