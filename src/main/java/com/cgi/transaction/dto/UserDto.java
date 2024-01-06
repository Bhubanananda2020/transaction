package com.cgi.transaction.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cgi.transaction.domain.User;

@Component
public class UserDto {

	private String firstName;

	private String lastName;

	private String accountNumber;

	public UserDto entityToDto(User user) {
		UserDto userDto = new UserDto();
		if (Objects.nonNull(user)) {
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			String maskAccountNumber = maskAccountNumber(user.getUserAccounts().get(0).getAccountNumber(), 2);
			userDto.setAccountNumber(maskAccountNumber);
		}
		return userDto;
	}

	public List<UserDto> entityToDto(List<User> user) {
		return user.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	private String maskAccountNumber(long accountNumber, int visibleDigits) {
		String accountNumberStr = String.valueOf(accountNumber);
		int length = accountNumberStr.length();
		if (visibleDigits >= length) {
			return accountNumberStr;
		}
		int startIndex = length - visibleDigits;
		char[] maskedChars = new char[startIndex];
		Arrays.fill(maskedChars, '*');
		return new StringBuffer().append(maskedChars).append(accountNumberStr.substring(startIndex)).toString();
	}

	public UserDto() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
