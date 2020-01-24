package com.ebank.eBanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.eBanking.model.CustomerEntity;
import com.ebank.eBanking.model.PayeeEntity;
import com.ebank.eBanking.repository.PayeeRepository;

@Service
public class PayeeService implements IPayeeService{
	
	@Autowired
	PayeeRepository payeeRepo;

	@Override
	public PayeeEntity payee(int accountno) {
		PayeeEntity payeeEntity = payeeRepo.payee(accountno);
		if (payeeEntity!=null)
				return payeeEntity;
		return payeeEntity;
	}
	
	public PayeeEntity createPayee(PayeeEntity entity){
		PayeeEntity payee = payeeRepo.payee(entity.getAccountNo());
		if (payee==null){
			payeeRepo.save(entity);
			return entity;
		}
		else
			return payee;
	}

	@Override
	public PayeeEntity updatePayeeBalance(float amount, int accountno) {
		PayeeEntity payeeEntity = payeeRepo.payee(accountno);
		float remBalance = payeeEntity.getBalance() + amount;
		int rowsUpdates = payeeRepo.updatePayeeBalance(remBalance, accountno);
		return payeeEntity;
	}
}
