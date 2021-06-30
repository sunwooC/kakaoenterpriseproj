package com.kakaoenterprise.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kakaoenterprise.config.auth.PrincipalDetails;
import com.kakaoenterprise.domain.user.RoleType;
import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 1. AccessToekn으로 회원정보를 받았다는 의미
		log.debug("AccessToken: {}", userRequest.getAccessToken().getTokenValue());
		OAuth2User oauth2User = super.loadUser(userRequest);
		log.debug("getAttributes: {}", oauth2User.getAttributes());

		return processOAuth2User(userRequest, oauth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
		// 1번 통합 클래스를 생성
		log.debug("getClientName: {}", userRequest.getClientRegistration().getClientName());

		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getClientName().equals("Kakao")) {
			
			KakaoInfo kakao = new KakaoInfo((Map<String, Object>) (oauth2User.getAttributes())); 
			String accessToken = userRequest.getAccessToken().getTokenValue();
			String refreshToken = MyDefaultAuthorizationCodeTokenResponseClient.map.get(accessToken);
			kakao.setAccessToekn(accessToken);
			kakao.setRefreshToken(refreshToken);
			kakao.setSysid("Kakao");
			oAuth2UserInfo = kakao;
		}

		// 2번 최초 : 회원가입 + 로그인 최초X : 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());

		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());

		if (userEntity != null) {
			log.debug("회원정보가 있습니다. 바로 로그인 합니다.");
			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
		}
		log.debug("최초 사용자,자동 회원가입을 진행 후 자동 로그인");
		User user = User.builder()
				.username(oAuth2UserInfo.getUsername())
				.nickname(oAuth2UserInfo.getUsername())
				.argrange(oAuth2UserInfo.getArgrange())
				.password(encPassword)
				.email(oAuth2UserInfo.getEmail())
				.refreshToken(oAuth2UserInfo.getRefreshToken())
				.accessToekn(oAuth2UserInfo.getAccessToekn())
				.sysid(oAuth2UserInfo.getSysid())
				.role(RoleType.USER)
				
				.build();
		userEntity = userRepository.save(user);
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}

}
