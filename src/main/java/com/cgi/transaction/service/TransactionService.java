package com.cgi.transaction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.transaction.domain.Transaction;
import com.cgi.transaction.dto.TransactionDto;
import com.cgi.transaction.repository.TransactionDao;

@Service
public class TransactionService {
	Logger log = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private TransactionDto transactionDto;

	public List<Transaction> createTransactions(List<Transaction> transactions) {
		List<Transaction> response = null;
		try {
			log.debug("calling transactionDao for save the transactions ");
			response = this.transactionDao.saveAll(transactions);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	public List<TransactionDto> getTransactions(Date startDate, Date endDate) {
		List<TransactionDto> transactionDto = new ArrayList<TransactionDto>();

		if (Objects.isNull(endDate)) {
			endDate = new Date();
		}

		log.debug("calling transactionDao for get transaction ");
		List<Transaction> transactions = this.transactionDao.getAllTransaction(startDate.getTime(),  endDate.getTime());

		log.debug("calling transactionDto for converting entity to DTO");
		transactionDto = this.transactionDto.entityToDto(transactions);

		return transactionDto;
	}

}