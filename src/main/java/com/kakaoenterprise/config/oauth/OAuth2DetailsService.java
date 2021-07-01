package com.kakaoenterprise.config.oauth;

import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

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
		log.debug("AccessToken: {}", userRequest.getAccessToken().getTokenValue());
		OAuth2User oauth2User = super.loadUser(userRequest);
		log.debug("getAttributes: {}", oauth2User.getAttributes());

		return processOAuth2User(userRequest, oauth2User);
	}

	@Transactional
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
		// 1번 통합 클래스를 생성
		log.debug("getClientName: {}", userRequest.getClientRegistration().getClientName());

		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getClientName().equals("Kakao")) {

			KakaoInfo kakao = new KakaoInfo((Map<String, Object>) (oauth2User.getAttributes()));
			String accessToken = userRequest.getAccessToken().getTokenValue();
			String refreshToken = MyDefaultAuthorizationCodeTokenResponseClient.map.get(accessToken);
			MyDefaultAuthorizationCodeTokenResponseClient.map.remove(accessToken);
			kakao.setAccessToekn(accessToken);
			kakao.setRefreshToken(refreshToken);
			kakao.setSysid("Kakao");
			oAuth2UserInfo = kakao;
		}

		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());

		User user = User.builder().username(oAuth2UserInfo.getUsername()).nickname(oAuth2UserInfo.getUsername())
				.agerange(oAuth2UserInfo.getAgerange()).password(encPassword).email(oAuth2UserInfo.getEmail())
				.refreshToken(oAuth2UserInfo.getRefreshToken()).accessToekn(oAuth2UserInfo.getAccessToekn())
				.sysid(oAuth2UserInfo.getSysid()).role(RoleType.USER)

				.build();
		// 2번 최초 : 회원가입 + 로그인 최초X : 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());

		if (userEntity != null) {
			log.debug("회원정보가 있습니다. 바로 로그인 합니다.");
			userEntity.setRefreshToken(user.getRefreshToken());
			userEntity.setAccessToekn(user.getAccessToekn());
			userEntity.setNickname(user.getNickname());
			userEntity.setAgerange(user.getAgerange());
			userRepository.save(userEntity);
			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
		}
		log.debug("최초 사용자,자동 회원가입을 진행 후 자동 로그인");
		log.debug(oAuth2UserInfo.getAgerange());

		userEntity = userRepository.save(user);
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}

}
