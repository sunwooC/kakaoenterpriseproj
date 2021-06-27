package com.kakaoenterprise.web.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.dto.UserDto;
import com.kakaoenterprise.web.dto.UserUpdateReqDto;
import com.kakaoenterprise.web.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    
	/*
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
*/

	@Override
	public Page<UserDto> findAll(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		return users.map(UserDto::new);
	}
	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElse(null);
	}

	@Override
	public void update(UserUpdateReqDto account) {
		userRepository.update(account.getNickname(),account.getId());
	}

	
	@Override
	public void deleteBySnsid(Long id) {
		userRepository.deleteBySnsid(id);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	
}
