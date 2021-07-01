package com.kakaoenterprise.web.controll;

import  org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class WebLoginController {
	@RequestMapping("/")
	public String login() {
	    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    //if(authentication == null) return "/login";	    
		//return "/user/userlist";
		return "/login";	    
	}

	@RequestMapping("/user/userlist")
	public String userList() {
		return "/user/userlist";
	}

}
