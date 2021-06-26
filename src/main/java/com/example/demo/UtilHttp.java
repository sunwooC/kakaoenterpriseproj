package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class UtilHttp {
	private HttpHeaders headers ;
	private MultiValueMap<String,String> params;
	
	private String url;
	
	public void addHeader(String key,String value) {
		headers.add(key, value);
	}
	public void addParm(String key,String value) {
		params.add(key, value);
	}
	public void post() {
		RestTemplate rt = new RestTemplate();	
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<>(params,headers);
		ResponseEntity<KakaoAuthToken> response = rt.exchange("https://kauth.kakao.com/oauth/token",HttpMethod.POST,req,KakaoAuthToken.class);
		
	}
	public void get() {
		
	}
	
}
