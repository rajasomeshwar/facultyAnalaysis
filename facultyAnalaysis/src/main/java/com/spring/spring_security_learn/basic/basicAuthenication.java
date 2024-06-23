package com.spring.spring_security_learn.basic;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
//@Configuration
public class basicAuthenication {
	@Bean
	SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
	//	http.formLogin();
		http.httpBasic();
		http.authorizeHttpRequests( auth -> auth.anyRequest().authenticated());
      http.csrf().disable();
      http.headers().frameOptions().sameOrigin();
		 http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      return http.build();
	}
}
