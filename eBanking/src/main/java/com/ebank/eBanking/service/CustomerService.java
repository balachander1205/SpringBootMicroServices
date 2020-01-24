package com.ebank.eBanking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.eBanking.model.CustomerEntity;
import com.ebank.eBanking.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService{
	@Autowired
	CustomerRepository repository;

	public static <T> List<T> getCollectionFromIteralbe(Iterable<T> itr) {
		// Create an empty Collection to hold the result
		List<T> cltn = new ArrayList<T>();

		// Use iterable.forEach() to
		// Iterate through the iterable and
		// add each element into the collection
		itr.forEach(cltn::add);

		// Return the converted collection
		return cltn;
	}

	public List<CustomerEntity> getAllCustomers() {
		Iterable<CustomerEntity> customers = repository.findAll();
		List<CustomerEntity> customersList = getCollectionFromIteralbe(customers);
		return customersList;
	}

	public CustomerEntity getCustomerById(Long id) {
		Optional<CustomerEntity> customer = repository.findById(id);

		if (customer.isPresent()) {
			return customer.get();
		} else {
			System.out.println("No employee record exist for given id");
		}
		return customer.get();
	}

	public CustomerEntity createCustomer(CustomerEntity customer) {
		if (customer!=null) {
			repository.save(customer);
		} else {
			System.out.println("{ Customer already created with account number : " + customer.getAccountno());
		}
		return customer;
	}

	@Override
	public CustomerEntity searchCstByAccountNo(int id) {
		CustomerEntity cusomerEntity = (CustomerEntity)repository.searchCstByAccountNo(id);
		return cusomerEntity;
	}

	@Override
	public int updateBalance(float balance, int accountno) {
		CustomerEntity customerEntity = (CustomerEntity)repository.searchCstByAccountNo(accountno);
		float remBalance = customerEntity.getBalance() - balance;
		int rowsUpdated = repository.updateBalance(remBalance, accountno);
		return rowsUpdated;
	}
}
