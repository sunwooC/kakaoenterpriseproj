package com.kakaoenterprise.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.kakaoenterprise.config.auth.RestLoginFailureHandler;
import com.kakaoenterprise.config.auth.RestLoginSuccessHandler;
import com.kakaoenterprise.config.oauth.OAuth2DetailsService;

//import com.kakaoenterprise.config.auth.RestLoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private RestLoginSuccessHandler restLoginSuccessHandler;
	@Autowired
	private RestLoginFailureHandler restLoginFailureHandler;

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and().exceptionHandling().accessDeniedPage("/");
		http.formLogin()
			.loginPage("/")
			.loginProcessingUrl("/login").permitAll();
		
			//.successHandler(restLoginSuccessHandler) 
			//.failureHandler(restLoginFailureHandler)
		http.oauth2Login()
		.userInfoEndpoint()
		.userService(oAuth2DetailsService);
		
		/*
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
				.formLogin()
				.loginPage("/index.html")
				.loginProcessingUrl("/login")
				.permitAll()
			*/
	}
	/*
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Qualifier("oauth2RestTemplate")
    public RestTemplate oauth2RestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null && auth.isAuthenticated() && auth instanceof OAuth2AuthenticationToken) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> details = (Map<String, Object>) ((OAuth2AuthenticationToken) auth).getPrincipal().getAttributes().get("details");
                    String tokenValue = (String) details.get("tokenValue");
                    if (tokenValue != null) {
                        request.getHeaders().add("Authorization", "Bearer " + tokenValue);
                    }
                }
                return execution.execute(request, body);
            }
        });
        return restTemplate;
    }
    */
	
}
