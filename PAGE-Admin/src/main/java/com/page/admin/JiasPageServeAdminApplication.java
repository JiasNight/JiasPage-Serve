package com.page.admin;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan(basePackages = {"com.page.*"})
public class JiasPageServeAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiasPageServeAdminApplication.class, args);
	}

}
