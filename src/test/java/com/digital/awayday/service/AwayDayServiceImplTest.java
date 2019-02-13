package com.digital.awayday.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.digital.awayday.AppClient;
import com.digital.awayday.exception.AwayDayException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClient.class, loader = AnnotationConfigContextLoader.class)
public class AwayDayServiceImplTest {
	
	@Autowired
	AwayDayService awayDayService;
	
	@Test
	public void distributionTasks() throws AwayDayException {
		
		//AwayDayService awayDayService = new AwayDayServiceImpl();
		TaskDistributionService taskDistService = awayDayService.createActivities();
		assertNotNull(taskDistService);
	}
	
}
