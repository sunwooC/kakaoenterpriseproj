package com.kakaoenterprise.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.dto.UserDto;
import com.kakaoenterprise.web.dto.UserUpdateReqDto;
import com.kakaoenterprise.web.repository.UserRepository;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	//private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get(); // 1차 캐시
		String encPassword = "";//bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		return userEntity;
	} // 더티체킹
	@Transactional
	public List<UserDto> findAll(Integer page, Integer size){
		List<UserDto> userDtos = new ArrayList<>();		
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
		List<User> list = userRepository.findAll(pageRequest).getContent();
	     for (User origin : list) {
	    	 UserDto target = new UserDto();
	         BeanUtils.copyProperties(origin, target);
	         /*
	         if (pageable != null && origin.getShopImages().iterator().hasNext()) 
	             target.setShopImage(origin.getShopImages().iterator().next());
	         */  
	         userDtos.add(target);
	       }
	     
		return userDtos;
	}
}






