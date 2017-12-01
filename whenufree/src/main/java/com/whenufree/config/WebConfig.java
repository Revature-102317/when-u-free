package com.whenufree.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class WebConfig {

	@Bean
	@Scope(value="session")
	public List<String> defaultTimes(){
		return new ArrayList<String>();
	}
	
}
