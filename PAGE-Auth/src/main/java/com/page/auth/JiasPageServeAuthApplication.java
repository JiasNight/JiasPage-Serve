package com.page.auth;

import com.page.common.configuration.JwtConfiguration;
import com.page.common.configuration.TransferConfiguration;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan(basePackages = {"com.page.*"})
public class JiasPageServeAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(JiasPageServeAuthApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(TransferConfiguration config) {
    return args -> {
      System.out.println("Property value: " + config.getAesKey());
    };
  }
}
