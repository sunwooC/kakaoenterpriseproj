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
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/**").permitAll().antMatchers("/login/**").permitAll().anyRequest().authenticated().and()
				.exceptionHandling().accessDeniedPage("/");
		http.formLogin().loginPage("/").loginProcessingUrl("/login").permitAll();
		// .successHandler(restLoginSuccessHandler)
		// .failureHandler(restLoginFailureHandler)
		http.oauth2Login().disable();
		/*
		 * /* .tokenEndpoint(token -> token
		 * .accessTokenResponseClient(this.accessTokenResponseClient()) )
		 */
		/*
		 * .userInfoEndpoint() .userService(oAuth2DetailsService);
		 * 
		 * .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		 * .anyRequest().permitAll() .and() .formLogin() .loginPage("/index.html")
		 * .loginProcessingUrl("/login") .permitAll()
		 */

	}

}
