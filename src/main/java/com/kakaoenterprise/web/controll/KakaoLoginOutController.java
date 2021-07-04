package com.kakaoenterprise.web.controll;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.kakaoenterprise.config.RestConfig;
import com.kakaoenterprise.config.oauth.KakaoAuthToken;

@Controller
public class KakaoLoginOutController {
	
	/*
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String redirectUri;

	@Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
	private String tokenUri;
	private String contentType = "application/x-www-form-urlencoded;charset=utf-8";

	@Value("${kakao.userMeUri}")
	private String userMeUri;

	@Value("${kakao.userLogoutUri}")
	private String userLogoutUri;
	@Value("${kakao.userUnlinkUri}")
	private String userUnlinkUri;
	@Autowired
	private RestConfig restConfig;


	@GetMapping("/login/oauth2/code/kakao")
	public String callback(String code, String error) {
		HashMap map = new HashMap();
		map.put("code", code);
		postOauthToken(code);
		return "/user/userlist.html";
	}

	public void postOauthToken(String code) {
		RestTemplate rt = restConfig.restTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", clientId);
		params.add("redirect_uri", redirectUri);
		params.add("code", code);
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
		ResponseEntity<KakaoAuthToken> response = rt.exchange(tokenUri, HttpMethod.POST, req, KakaoAuthToken.class);
		System.out.println(response.getBody());
		postUserMe(response.getBody());
	}

	public void postUserMe(KakaoAuthToken token) {
		RestTemplate rt = restConfig.restTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", contentType);
		headers.add("Authorization", "Bearer " + token.getAccess_token());
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(userMeUri, HttpMethod.POST, req, String.class);
		System.out.println(response.getBody());
		// logout(token.getAccess_token());
	}

	@GetMapping("/logout")
	public void logout(String token) {
		RestTemplate rt = restConfig.restTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(userLogoutUri, HttpMethod.POST, req, String.class);
		System.out.println(response);
	}

	@GetMapping("/unlink")
	public void unlink(String token) {
		RestTemplate rt = restConfig.restTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(userUnlinkUri, HttpMethod.POST, req, String.class);
		System.out.println(response);
	}

	@GetMapping("/refresh-token")
	public void repp(String refresh_token) {
		RestTemplate rt = restConfig.restTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", contentType);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("refresh_token", refresh_token);
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
		ResponseEntity<KakaoAuthToken> response = rt.exchange(tokenUri, HttpMethod.POST, req, KakaoAuthToken.class);
	}
	*/


}
