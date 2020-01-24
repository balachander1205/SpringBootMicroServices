package com.ebank.eBanking;

import java.util.Arrays;

import org.hibernate.annotations.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.ebank.eBanking.model")
@EnableFeignClients
@EnableDiscoveryClient
//@LoadBalanced
@EnableCircuitBreaker
@EnableSwagger2
public class EBankingApplication{
	
	@Autowired
    private ApplicationContext appContext;
	
	@Value("${spring.application.name}")
	private String appName;
	
//	ProfilesTest profilesTest;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EBankingApplication.class, args);	
		ProfilesTest profilesTest = new ProfilesTest();
		System.out.println(profilesTest.getAppName());
	}
}
