package com.cgi.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cgi.transaction.domain.Bank;
import com.cgi.transaction.domain.Currency;
import com.cgi.transaction.domain.Status;
import com.cgi.transaction.domain.Transaction;
import com.cgi.transaction.domain.User;
import com.cgi.transaction.domain.UserAccount;
import com.cgi.transaction.service.TransactionService;
import com.cgi.transaction.service.UserService;

@SpringBootApplication
public class TransactionApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User userData1 = getUserData("John", "Smith", "John@company.com", "1970-01-23", Bank.TD);
		User userData2 = getUserData("Jane", "doe", "janedao@company.com", "1970-01-23", Bank.TD);

		User userData3 = getUserData("John2", "Smith", "John@company2.com", "1970-02-23", Bank.TD);
		User userData4 = getUserData("Jane2", "doe", "janedao@company2.com", "1970-02-23", Bank.TD);

		User userData5 = getUserData("John3", "Smith", "John@company3.com", "1970-03-23", Bank.CIBC);
		User userData6 = getUserData("Jane3", "doe", "janedao@company3.com", "1970-03-23", Bank.CIBC);

		User userData7 = getUserData("John4", "Smith", "John@company4.com", "1970-04-23", Bank.RBC);
		User userData8 = getUserData("Jane4", "doe", "janedao@company4.com", "1970-04-23", Bank.RBC);

		List<User> createUsersData = List.of(userData1, userData2, userData3, userData4, userData5, userData6,
				userData7, userData8);
		this.userService.createUsers(createUsersData);

		Transaction transactionData1 = getTransactionData(1639503071000L, 100001L, 100002L, 100L, Currency.CAD,
				"Utility bill", Status.COMPLETED);
		Transaction transactionData2 = getTransactionData(1639486575000L, 100003L, 100004L, 100L, Currency.USD, "Rent",
				Status.PENDING);
		Transaction transactionData3 = getTransactionData(1639478930000L, 100005L, 100006L, 300L, Currency.USD,
				"Insurance premium", Status.IN_PROGRESS);
		Transaction transactionData4 = getTransactionData(1638997755000L, 100007L, 100008L, 100L, Currency.CAD,
				"Cash transfer", Status.REJECTED);

		List<Transaction> createTransactionsData = List.of(transactionData1, transactionData2, transactionData3,
				transactionData4);
		this.transactionService.createTransactions(createTransactionsData);
	}

	User getUserData(String firstName, String lastName, String email, String dateOfBirth, Bank bank) {
		User user = new User();
		UserAccount userAccount = new UserAccount();
		userAccount.setBank(bank);

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setDateOfBirth(dateOfBirth);

		userAccount.setUser(user);
		user.setUserAccounts(List.of(userAccount));
		return user;
	}

	Transaction getTransactionData(Long date, Long senderUserId, Long receiverUserId, Long amount,
			Currency currencyCode, String comments, Status status) {
		Transaction transaction = new Transaction();
		User senderUser = new User();
		senderUser.setUserId(senderUserId);

		User receiverUser = new User();
		receiverUser.setUserId(receiverUserId);

		transaction.setDate(date);
		transaction.setSender(senderUser);
		transaction.setReceiver(receiverUser);

		transaction.setAmount(amount);
		transaction.setCurrencyCode(currencyCode);
		transaction.setComments(comments);
		transaction.setStatus(status);
		return transaction;
	}
}
