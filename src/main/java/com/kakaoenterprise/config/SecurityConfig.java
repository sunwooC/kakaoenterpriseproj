package com.kakaoenterprise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import com.kakaoenterprise.config.oauth.MyDefaultAuthorizationCodeTokenResponseClient;
import com.kakaoenterprise.config.oauth.OAuth2DetailsService;

//import com.kakaoenterprise.config.auth.RestLoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	private final OAuth2DetailsService oAuth2DetailsService;

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/", "/css/**", "/js/**","/login/**").permitAll()
			.antMatchers("/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated();
			//.and().exceptionHandling().accessDeniedPage("/login");
		http.formLogin()
			.loginPage("/");
			//.loginProcessingUrl("/login").permitAll();
		//http.oauth2Login().disable();
		
		http.oauth2Login().defaultSuccessUrl("/user/userlist")
			.userInfoEndpoint().userService(oAuth2DetailsService);
		http.oauth2Login().tokenEndpoint().accessTokenResponseClient(new MyDefaultAuthorizationCodeTokenResponseClient()); 
	}

}
