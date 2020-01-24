package com.ebank.eBanking.service;

import org.springframework.data.repository.query.Param;

import com.ebank.eBanking.model.PayeeEntity;

public interface IPayeeService {
	public PayeeEntity payee(int accountno);	
	public PayeeEntity updatePayeeBalance(float amount,int accountno);

//	public PayeeEntity createPayee(PayeeEntity payeeEntity);
}
