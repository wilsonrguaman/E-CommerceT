package com.shopme.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new ShopmeUserDetailsService();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder () {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}	
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
}
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/users/**").hasAuthority("Admin")
				.requestMatchers("/categories/**", "/brands/**").hasAnyAuthority("Admin","Editor")
				.requestMatchers("/products/**").hasAnyAuthority("Admin","Editor","Salesperson","Shipper")
				.anyRequest().authenticated())
				.formLogin((form) -> form
        			.loginPage("/login") 
        			.usernameParameter("email")
        			.permitAll())
				.logout((logout) -> logout
						.permitAll())
				.rememberMe((remember) -> remember
						.key("AbcDefgHiJKlmnOpqrs_1234567890")
						.tokenValiditySeconds(7 * 24 * 60 * 60)
					
						
        		);

		return http.build();
	}

	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
  }
}

