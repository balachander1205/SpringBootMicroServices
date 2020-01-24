package com.ebank.eBankingOTP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.eBankingOTP.model.OTPModel;
import com.ebank.eBankingOTP.repository.OTPRepo;

@Service
public class OtpService implements IOtpService{
	@Autowired
	OTPRepo otpRepo;

	@Override
	public OTPModel getOTPData(String mobileno) {
		OTPModel otpModelData = otpRepo.getOtpData(mobileno);
		return otpModelData;
	}	
}