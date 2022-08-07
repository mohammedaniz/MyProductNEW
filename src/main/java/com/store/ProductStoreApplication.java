package com.store;

import org.springframework.boot.SpringApplication;
//(exclude = SecurityAutoConfiguration.class)
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ProductStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductStoreApplication.class, args);
	}
	

}
