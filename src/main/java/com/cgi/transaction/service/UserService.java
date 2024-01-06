package com.cgi.transaction.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.transaction.domain.User;
import com.cgi.transaction.repository.UserDao;

@Service
public class UserService {

	Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;

	public List<User> createUsers(List<User> users) {
		List<User> response = null;
		try {
			log.debug("calling userDao for save the users ");
			response = this.userDao.saveAll(users);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	public List<User> getUsers() {
		log.debug("calling userDao for get users ");
		return this.userDao.findAll();
	}
}