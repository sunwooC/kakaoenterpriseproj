package com.kakaoenterprise.web.auth.dto;

import com.kakaoenterprise.domain.user.User;

import lombok.Data;

// Valid 나중에 처리하자.

@Data
public class AuthJoinReqDto {
	private String username;
	private String nickname;
	private String password;
	private String email;

	public User toEntity() {
		return User.builder()
				.username(username)
				.nickname(nickname)
				.password(password)
				.email(email).build();
	}
}
