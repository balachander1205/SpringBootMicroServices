package com.ebank.eBankingOTP.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.eBankingOTP.model.OTPModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ebank")
@Api(value = "eBanking Management System - (OTP)", description = "Operations pertaining to customer in eBanking otp Management System")
public class OTPController {
	@PostMapping("/getotp")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The request has succeeded or (your message)"),
			@ApiResponse(code = 401, message = "The request requires user authentication or (your message)"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden or (your message)"),
			@ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI or (your message)") })
	public OTPModel getOtp(@RequestBody OTPModel otpModel) {
		OTPModel otpModel2 = new OTPModel();
		otpModel2.setMobileno(otpModel.getMobileno());
		try {
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
			otpModel2.setOtp(String.valueOf(number));
			long currTM = System.currentTimeMillis();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
			Date date = new Date(currTM);
			String fmtDate = dateFormat.format(date);
			Date date1;
			date1 = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss").parse(fmtDate);
			otpModel2.setCreatetime(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return otpModel2;
	}
}
