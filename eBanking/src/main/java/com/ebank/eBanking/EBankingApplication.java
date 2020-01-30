package com.ebank.eBanking;

import java.util.Arrays;

import com.ebank.eBanking.security.JWTAuthorizationFilter;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/ebank/user").permitAll()
					.anyRequest().authenticated();
		}
	}
}
