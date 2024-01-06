package com.cgi.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.transaction.controller.TransactionController;
import com.cgi.transaction.dto.TransactionDto;
import com.cgi.transaction.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

	@Mock
	private TransactionService transactionService;

	@InjectMocks
	private TransactionController fixture;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(fixture).build();
	}

	@Test
	public void getTransaction_return200() throws Exception {

		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionId(1L);
		when(transactionService.getTransactions(any(), any())).thenReturn(List.of(transactionDto));

		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/transaction/").param("startDate", "2021-12-03")
						.param("endDate", "2021-12-03").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		System.out.println(response.getStatus());
		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(200);
	}
}