package com.cgi.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.transaction.domain.Transaction;
import com.cgi.transaction.dto.TransactionDto;
import com.cgi.transaction.repository.TransactionDao;
import com.cgi.transaction.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@Mock
	private TransactionDao transactionDao;

	@Mock
	private TransactionDto transactionDto;

	@InjectMocks
	private TransactionService fixture;

	
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(fixture).build();
	}

	@Test
	public void getTransaction_return200() throws Exception {

		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionId(1L);
		Transaction transaction = new Transaction();
		transaction.setId(1L);

		when(transactionDao.getAllTransaction(any(), any())).thenReturn(List.of(transaction));
		List<TransactionDto> transactionDtoResponse = this.fixture.getTransactions(new Date(), null);
		assertThat(transactionDtoResponse).isNotNull();
	}
	
	@Test
	public void createTransactions_return200() throws Exception {
		
		Transaction transaction = new Transaction();
		transaction.setId(1L);
		
		when(transactionDao.saveAll(anyList())).thenReturn(List.of(transaction));
		List<Transaction> createTransactions = this.fixture.createTransactions(List.of(transaction));
		
		assertThat(createTransactions.size()).isGreaterThanOrEqualTo(1);
	}

}
