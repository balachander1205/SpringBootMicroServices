package com.eBank.eBankingEurekaServerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EBankingEurekaServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingEurekaServerAppApplication.class, args);
	}
}
