package com.microservices.CurrenyExchangeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class CurrenyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenyExchangeServiceApplication.class, args);
	}

}
