package com.ebank.eBanking.proxy;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ebank.eBanking.model.OTPModel;

@FeignClient(name="ebanking-otp-server")
@RibbonClient(name="ebanking-otp-server")
public interface IOtpProxy {
	@PostMapping("/ebank/getotp")
	public OTPModel getOtp(OTPModel otpModel);
}
