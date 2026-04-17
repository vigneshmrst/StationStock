package com.ramsuvegatech.stationstock.securityconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	public static final String[] PUBLIC_URLS = {
			"/api/auth/**",
	};
	
	// ── Password encoding ─────────────────────────────────────────────────────
	
@Bean
	public PasswordEncoder passwordEncoder() {
	// BCrypt with strength 12 — strong without being too slow (~250ms per hash)
		return new BCryptPasswordEncoder(12);
	}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
	// Disable CSRF — stateless REST API, no session cookies
	http.csrf(csrf -> csrf.disable())
	
	// Authorization rules
	.authorizeHttpRequests(auth -> auth
            .requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated());
	
	 return http.build();
}
}