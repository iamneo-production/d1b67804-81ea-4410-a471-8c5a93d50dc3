package com.curator.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.curator.tokenvalidations.JwtFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {
	private JwtFilter jwtFilter;
	@Autowired
	public SecurityConfigurations(@Lazy JwtFilter jwtFilter)
	{
		this.jwtFilter=jwtFilter;
	}
	@Bean
	public SecurityFilterChain authorize(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf->csrf.disable())
						   .authorizeHttpRequests(authorizeHttpRequests-> authorizeHttpRequests
								   .anyRequest().authenticated())
					   		   .sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						   		   .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
						   .build();
	}
}
