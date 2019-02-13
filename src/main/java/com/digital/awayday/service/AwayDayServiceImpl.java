package com.digital.awayday.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.awayday.dao.AwayDayDaoImpl;
import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.Task;

/**
 * Service is used load the task from resources and generate away day
 * @author Shubham Agarwal
 *
 */
@Service
public class AwayDayServiceImpl implements AwayDayService {

	@Autowired
	AwayDayDaoImpl awayDayDao;
	
	@Autowired
	TaskDistributionService taskDistributionService;
	
	@Override
	public TaskDistributionService createActivities() throws AwayDayException {
		List<Task> tasks;
			
			tasks = awayDayDao.loadTasksFromResources();
			TaskDistributionService awayDay = taskDistributionService.initialize(
					LocalTime.of(9, 00), 
					LocalTime.of(12, 00), 
					LocalTime.of(13, 00),
					LocalTime.of(16, 00), 
					60, tasks);
			awayDay.addTasks(tasks);
		return awayDay;
	}
}