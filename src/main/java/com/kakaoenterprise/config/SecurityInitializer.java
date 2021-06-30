package com.kakaoenterprise.config;

import java.io.ObjectInputFilter.Config;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(SecurityConfig.class, Config.class);
	}

}