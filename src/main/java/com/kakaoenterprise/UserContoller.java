package com.kakaoenterprise;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@PropertySource(value={"application.yml"})
public class UserContoller {
/*
	@GetMapping("/logout")
	public void logout(String token) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(userLogoutUri,HttpMethod.POST,req,String.class);
		System.out.println(response);
	}
	@GetMapping("/unlink")
	public void unlink(String token) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(userUnlinkUri,HttpMethod.POST,req,String.class);
		System.out.println(response);
	}
	@GetMapping("/refresh-token")
	public void repp(String refresh_token) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", contentType);
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("refresh_token", refresh_token);
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<>(params,headers);
		ResponseEntity<KakaoAuthToken> response = rt.exchange(tokenUri,HttpMethod.POST,req,KakaoAuthToken.class);
	}
*/
}
