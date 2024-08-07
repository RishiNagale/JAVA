package com.example.java_project.config;

import com.example.java_project.jwt.JwtAuthenticationEntryPoint;
import com.example.java_project.jwt.JwtAuthenticationFilter;

import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityFilterConfig {
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
	
	@Autowired
    private JwtAuthenticationFilter filter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
    	
    	security.csrf(csrf -> csrf.disable())
        .authorizeRequests()
        .requestMatchers("/h2-console/**").permitAll()
        .requestMatchers("/welcome/login").permitAll()
        .requestMatchers("/auth/**").authenticated()
        .anyRequest()
        .authenticated()
        .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers().frameOptions().disable();
    	
    	security.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	return security.build();

    }
    
    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	
    	return provider;
    }
}
