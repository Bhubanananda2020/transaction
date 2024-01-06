package com.cgi.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.transaction.domain.User;
import com.cgi.transaction.repository.UserDao;
import com.cgi.transaction.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserService fixture;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(fixture).build();
	}

	@Test
	public void getTransaction_return200() throws Exception {
		User user = new User();
		user.setFirstName("test");

		when(userDao.findAll()).thenReturn(List.of(user));

		List<User> userResponse = this.fixture.getUsers();
		assertThat(userResponse).isNotNull();
	}

	@Test
	public void createUsers_return200() throws Exception {
		User user = new User();
		user.setFirstName("test");
		when(userDao.saveAll(anyList())).thenReturn(List.of(user));
		List<User> createTransactions = this.fixture.createUsers(List.of(user));

		assertThat(createTransactions.size()).isGreaterThanOrEqualTo(1);
	}

}
