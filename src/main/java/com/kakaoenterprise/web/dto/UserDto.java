package com.kakaoenterprise.web.dto;

import com.kakaoenterprise.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
	private Long id;
	private String nickname;;
	private String email;
	private String argrange;;

	@Builder
	public UserDto(Long id, String nickname, String email, String argrange) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.argrange = argrange;
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.argrange = user.getArgrange();
	}
}
