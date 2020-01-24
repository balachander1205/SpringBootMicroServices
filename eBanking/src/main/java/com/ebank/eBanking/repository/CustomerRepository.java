package com.ebank.eBanking.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebank.eBanking.model.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
	@Query("SELECT cst FROM tbl_customer cst where cst.accountno = :id")
	CustomerEntity searchCstByAccountNo(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update tbl_customer cst set cst.balance = :balance where cst.accountno = :accountno")
	int updateBalance(@Param("balance") float balance, @Param("accountno") int accountno);
}
