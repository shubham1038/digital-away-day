package com.digital.awayday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.service.AwayDayService;
import com.digital.awayday.service.TaskDistributionService;

/**
 * Main class. Create spring container to start program which will 
 * internally run the logic to generates the away day
 * @author Shubham Agarwal
 *
 */
@Configuration
@ComponentScan(basePackages = "com.digital.awayday.*")
public class AppClient {
	
	@Autowired
	AwayDayService awayDayService;
	
	public static void main(String[] args) {
		
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppClient.class)) {
			AppClient client = context.getBean(AppClient.class);
			client.start();
		} catch (AwayDayException e) {
			System.out.println("There was an error executing away day:" + e.getLocalizedMessage());
		}
	}
	
	private void start() throws AwayDayException {
		
		TaskDistributionService awayDay = awayDayService.createActivities();
		System.out.println("Deloitte Digital Away Day:");
		System.out.println(awayDay);
	}
}
