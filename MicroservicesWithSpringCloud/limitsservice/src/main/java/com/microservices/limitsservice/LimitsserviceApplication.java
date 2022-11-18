package com.microservices.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class LimitsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitsserviceApplication.class, args);
	}

}
