package com.kakaoenterprise.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakaoenterprise.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);
	
}
