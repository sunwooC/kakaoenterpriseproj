package com.kakaoenterprise.config.oauth;

import java.util.Map;

public class KakaoInfo extends OAuth2UserInfo {

	public KakaoInfo(Map<String, Object> attributes) {
		super(attributes);
	}
	private String refreshToken;
	private String accessToekn;
	private String sysid;
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	@Override
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setAccessToekn(String accessToekn) {
		this.accessToekn = accessToekn;
	}
	@Override
	public String getAccessToekn() {
		return accessToekn;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	@Override
	public String getSysid() {
		return sysid;
	}
	
	@Override
	public String getId() {
		return attributes.get("id").toString();
	}


	
	
	@Override
	public String getName() {
		Map<String, Object> temp = (Map) attributes.get("properties");
		return (String) temp.get("nickname");
	}

	@Override
	public String getEmail() {
		Map<String, Object> temp = (Map) attributes.get("kakao_account");
		return (String) temp.get("email");
	}

	@Override
	public String getImageUrl() {
		Map<String, Object> temp = (Map) attributes.get("properties");
		return (String) temp.get("profile_image");
	}

	@Override
	public String getUsername() {
		return "Kakao_" + attributes.get("id").toString();
	}

	@Override
	public String getAgerange() {
		Map<String, Object> temp = (Map) attributes.get("kakao_account");
		return (String) temp.get("age_range");
	}

}
