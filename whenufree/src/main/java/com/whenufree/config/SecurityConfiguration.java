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

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.Arrays;


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
		
	http.cors()
	    .and()
	    .httpBasic().and()
       	    .authorizeRequests()
	    .antMatchers("/register").permitAll()
	    .anyRequest()
	    .authenticated()
	    .and().csrf()
	    .ignoringAntMatchers("/login", "/register")
	    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    // @Override
    // public void configure(WebSecurity web) throws Exception {
    // 	web
    // 	    .ignoring()
    // 	    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    // }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(new String[]{"http://ec2-18-216-0-70.us-east-2.compute.amazonaws.com"}));
        configuration.setAllowedMethods(Arrays.asList(new String[]{"HEAD",
		    "GET", "POST", "PUT", "DELETE", "PATCH"}));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList(new String[]{"Authorization", "Cache-Control", "Content-Type", "X-Requested-With", "X-XSRF-TOKEN"}));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
