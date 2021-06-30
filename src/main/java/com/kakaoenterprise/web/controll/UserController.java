package com.kakaoenterprise.web.controll;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

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

	@ApiOperation(value = "전체 회원 검색", notes="폐이징 및 정렬기능")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "검색 결과 페이지 (0..N)"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "페이지당 수"),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "정렬기준 형식(,asc|desc). " +
	                    "기본은 asc,여러 기준 정렬 가능" )
	})	
	@GetMapping("/api/v1/users")
	public ResponseEntity<Page<UserDto>> retrievePosts(
			@PageableDefault(sort = { "id" }, direction = Direction.ASC, size = 2)
			final Pageable pageable) {
		Page<UserDto> posts = userService.findAll(pageable);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@DeleteMapping("/api/v1/user/{id}")
	public ResponseEntity<?> deleteSnsId(@PathVariable Long snsid) {
		userService.deleteBySnsid(snsid);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	@DeleteMapping("/api/v1/user/{id}")
	public ResponseEntity<?> deleteId(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user.getSysid() != null) {
			// 카카오에 로그아웃
		}
		userService.deleteById(id);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	
}
