package com.kakaoenterprise.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.dto.UserDto;
import com.kakaoenterprise.web.dto.UserUpdateReqDto;

public interface UserService {

	Page<UserDto> findAll(Pageable pageable);

	void update(UserUpdateReqDto account);

	void deleteById(Long id);

	void deleteBySnsid(Long id);

	User findById(Long id);
}
