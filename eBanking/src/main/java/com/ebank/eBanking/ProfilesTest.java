package com.ebank.eBanking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class ProfilesTest {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Profile("dev")
	public String getAppName(){
		return this.appName;
	}
	
	@Profile("prod")
	public String getProdAppName(){
		return this.appName;
	}
	
	@Profile("test")
	public String getTestAppName(){
		return this.appName;
	}
}
