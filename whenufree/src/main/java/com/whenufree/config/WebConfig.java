package com.whenufree.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.whenufree.model.FriendGroup;

/*************
 * 
 * This configuration configures things to the scope of session
 * This allows users to not conflict with each other when they access
 * an element on the requests
 * 
 */
@EnableWebMvc
@Configuration
public class WebConfig {

	@Bean
	@Scope(value="session")
	public List<String> defaultTimes(){
		return new ArrayList<String>();
	}
	
	
	@Bean
	@Scope(value="session")
	public FriendGroup activeFriendGroup(){
		return new FriendGroup();
	}
	
}
