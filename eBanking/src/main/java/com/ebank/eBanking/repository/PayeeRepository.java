package com.ebank.eBanking.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebank.eBanking.model.PayeeEntity;

@Repository
public interface PayeeRepository extends CrudRepository<PayeeEntity, Long>{
	@Query("SELECT payee FROM tbl_payee payee WHERE payee.accountno = :id")
	PayeeEntity payee(@Param("id") int d);
	
	@Modifying
	@Transactional
	@Query("update tbl_payee payee set payee.balance = :balance where payee.accountno = :accountno")
	int updatePayeeBalance(@Param("balance") float amount, @Param("accountno") int accountno);
}
