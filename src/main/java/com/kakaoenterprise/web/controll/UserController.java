package com.kakaoenterprise.web.controll;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.auth.dto.AuthJoinReqDto;
import com.kakaoenterprise.web.dto.UserDto;
import com.kakaoenterprise.web.dto.UserUpdateReqDto;
import com.kakaoenterprise.web.service.AuthService;
import com.kakaoenterprise.web.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;
	private final AuthService authService;

	@PostMapping("/login/join")
	public ResponseEntity join(AuthJoinReqDto authJoinReqDto) {
		authService.join(authJoinReqDto.toEntity());
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	@PostMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody UserUpdateReqDto userUpdateReqDto) {
		userService.update(userUpdateReqDto);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	@GetMapping("/api/v1/users")
	public ResponseEntity retrievePosts(final Pageable pageable) {
		Page<UserDto> posts = userService.findAll(pageable);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@DeleteMapping("/api/v1/user/{id}")
	public ResponseEntity<?> deleteSnsId(@PathVariable Long snsid) {
		userService.deleteBySnsid(snsid);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteId(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user.getSysid() != null) {
			// ???????????? ????????????
		}
		userService.deleteById(id);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

}
