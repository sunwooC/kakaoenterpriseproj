package com.kakaoenterprise.web.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebLoginController {
	@RequestMapping("/")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/user/userlist")
	public String userList(){
		return "/user/userlist";
	}
	
	

}
