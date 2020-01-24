package com.ebank.eBanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EBankingAppCmdL implements CommandLineRunner{
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	ProfilesTest pt;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("{======================="+pt.getAppName()+"========================}");
	}
}
