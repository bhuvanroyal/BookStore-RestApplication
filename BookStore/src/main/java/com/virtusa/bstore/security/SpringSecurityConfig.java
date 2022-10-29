package com.virtusa.bstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
	
    auth.inMemoryAuthentication().withUser("shivani").password(passwordEncoder().encode("bokaro123")).roles("admin") 
     .and().withUser("lalitha").password(passwordEncoder().encode("vijag123")).roles("user");
	}
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception
	{ 
		http.authorizeRequests()
		.antMatchers("/cartRest/**").hasRole("user")
		.antMatchers("/adminRest/**").hasRole("admin")
		.antMatchers("/bookRest/**").hasAnyRole("admin","user")
		.antMatchers("/orderRest/**").hasAnyRole("user")
		.antMatchers("/userRest/**").hasAnyRole("user")
		.anyRequest().authenticated()
		.and().httpBasic();
		http.csrf().disable();
			
	}
	
	@Bean 
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
