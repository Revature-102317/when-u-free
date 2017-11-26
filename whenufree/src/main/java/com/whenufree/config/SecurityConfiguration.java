package com.whenufree.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder,
				 UserDetailsService userService){
	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	this.userDetailsService = userService;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
	auth
	    .userDetailsService(this.userDetailsService)
	    .passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		
	http.httpBasic().and()
	    .authorizeRequests()
	    .antMatchers("/**")
	    .authenticated();
    }

    // @Override
    // public void configure(WebSecurity web) throws Exception {
    // 	web
    // 	    .ignoring()
    // 	    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    // }

}
