package com.kakaoenterprise.web.dto;

import lombok.Data;

@Data
public class UserDto {
	private Integer id; // 시퀀스, auto_increment
	private String username;
	private String email;
}
