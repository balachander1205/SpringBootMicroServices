package com.ebank.eBankingOTP.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ebank.eBankingOTP.model.OTPModel;

@Repository
public interface OTPRepo extends CrudRepository<OTPModel, Long>{
	@Query("select otpdata from tbl_otp otpdata where otpdata.mobileno = :mobileno")
	OTPModel getOtpData(@Param("mobileno") String mobileno);
}
