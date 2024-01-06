package com.cgi.transaction.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.transaction.domain.Currency;
import com.cgi.transaction.domain.Status;
import com.cgi.transaction.domain.Transaction;

@Component
public class TransactionDto {

	@Autowired
	private UserDto userDto;

	private Long transactionId;
	private String date;

	private UserDto sender;

	private UserDto receiver;

	private Long amount;

	private Currency currencyCode;

	private String comments;

	private Status status;

	public TransactionDto entityToDto(Transaction transaction) {
		TransactionDto transactionDto = new TransactionDto();

		if (Objects.nonNull(transaction)) {
			UserDto sendUser = this.userDto.entityToDto(transaction.getSender());
			UserDto receiverUser = this.userDto.entityToDto(transaction.getReceiver());

			transactionDto.setTransactionId(transaction.getId());

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(new Date(transaction.getDate()));
			transactionDto.setDate(formattedDate);

			transactionDto.setAmount(transaction.getAmount());
			transactionDto.setSender(sendUser);
			transactionDto.setReceiver(receiverUser);
			transactionDto.setCurrencyCode(transaction.getCurrencyCode());
			transactionDto.setComments(transaction.getComments());
			transactionDto.setStatus(transaction.getStatus());
		}
		return transactionDto;
	}

	public List<TransactionDto> entityToDto(List<Transaction> transaction) {
		return transaction.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public TransactionDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UserDto getSender() {
		return sender;
	}

	public void setSender(UserDto sender) {
		this.sender = sender;
	}

	public UserDto getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDto receiver) {
		this.receiver = receiver;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Currency getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(Currency currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
