package com.ebank.eBankingOTP.service;

import com.ebank.eBankingOTP.model.OTPModel;

public interface IOtpService {
	public OTPModel getOTPData(String mobileno);
}
