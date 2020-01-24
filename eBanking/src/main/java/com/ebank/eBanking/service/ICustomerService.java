package com.ebank.eBanking.service;

import org.springframework.data.repository.query.Param;

import com.ebank.eBanking.model.CustomerEntity;

public interface ICustomerService {
	CustomerEntity searchCstByAccountNo(int id);
	int updateBalance(float balance, int accountno);
}
