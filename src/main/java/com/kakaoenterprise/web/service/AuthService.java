package com.kakaoenterprise.web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaoenterprise.domain.user.RoleType;
import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bBCryptPasswordEncoder;
	
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = bBCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
}
