package com.cgi.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cgi.transaction.domain.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT "
			+ "transaction_id, amount, comments, currency_code, transaction_date, status, receiver_user_id, sender_user_id "
			+ "FROM transaction " + "WHERE status IN ('IN_PROGRESS' , 'REJECTED', 'COMPLETED') "
			+ "AND transaction_date BETWEEN :startDate AND :endDate "
			+ "ORDER BY transaction_date ASC", nativeQuery = true)
	List<Transaction> getAllTransaction(@Param("startDate") Long startDate, @Param("endDate") Long endDate);
}
