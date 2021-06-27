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

    @Builder
    public UserDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}

