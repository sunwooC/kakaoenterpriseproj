package com.kakaoenterprise.web.controll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.kakaoenterprise.web.dto.UserDto;
import com.kakaoenterprise.web.service.UserService;

import lombok.RequiredArgsConstructor;


import java.util.List;



@RequiredArgsConstructor
@RestController
public class UserListController {
	
	private final UserService userService;
	
	@GetMapping("/api/v1/users")
	public List<UserDto> findAll(@RequestParam Integer page, Integer size){
		return userService.findAll(page,size);
	}
}
