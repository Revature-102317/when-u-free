package com.whenufree.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

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
@EnableScheduling
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

    /*@Bean
    public TaskScheduler taskScheduler() {
	return new ConcurrentTaskScheduler(); //single threaded by default
    }
    */	
}
