package com.ebank.eBankingOTP.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class EBankingOtpSwagger {
	@Bean
	public Docket eBankingOtpApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
//				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for ebanking otp generation",
                "1.0",
                "Terms of service",
                new Contact("Bala Chander", "http://github.com/balachander1205", "digumarthi.bala@gmail.conm"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", null);
        return apiInfo;
    }
}
