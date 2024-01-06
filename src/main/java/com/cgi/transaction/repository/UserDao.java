package com.cgi.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.transaction.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
