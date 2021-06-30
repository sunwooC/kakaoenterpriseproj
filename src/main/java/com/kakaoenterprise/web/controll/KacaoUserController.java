package com.kakaoenterprise.web.controll;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakaoenterprise.web.service.Impl.KakaoServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class KacaoUserController {
	
	private final KakaoServiceImpl kakaoServiceImpl;
	
	@GetMapping("/api/v1/access-toekns/{id}")
	public ResponseEntity<?> update(@PathVariable int id) {

		return new ResponseEntity<>(1, HttpStatus.OK);
	}
}
