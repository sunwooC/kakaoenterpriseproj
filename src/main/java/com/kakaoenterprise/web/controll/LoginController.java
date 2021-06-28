package com.kakaoenterprise.web.controll;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
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

import com.kakaoenterprise.KakaoAuthToken;

@Controller
public class LoginController {

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}") 
	private String clientId ;
	
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}") 
	private String redirectUri;

	@Value("${spring.security.oauth2.client.provider.kakao.token-uri}") 
	private String tokenUri;
	private String contentType ="application/x-www-form-urlencoded;charset=utf-8";
	private String userMeUri ="https://kapi.kakao.com/v2/user/me";
	private String userLogoutUri ="https://kapi.kakao.com/v1/user/logout";
	private String userUnlinkUri ="https://kapi.kakao.com/v1/user/unlink";
	/*
	@GetMapping("/login/oauth2/code/kakao")
	public String callback(String code,String error) {
		HashMap map = new HashMap();
		map.put("code", code);
		postOauthToken(code);
		return "/userList.html";
	}
	public void postOauthToken(String code) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", clientId);
		params.add("redirect_uri",redirectUri);
		params.add("code",code);
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<>(params,headers);
		ResponseEntity<KakaoAuthToken> response = rt.exchange(tokenUri,HttpMethod.POST,req,KakaoAuthToken.class);
		System.out.println(response.getBody());
		postUserMe(response.getBody());
	}
	public void postUserMe(KakaoAuthToken token) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", contentType);
		headers.add("Authorization", "Bearer "+token.getAccess_token());
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(userMeUri,HttpMethod.POST,req,String.class);
		System.out.println(response.getBody());
		//logout(token.getAccess_token());
	}
	*/
}
