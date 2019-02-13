package com.digital.awayday.service;

import java.time.LocalTime;
import java.util.List;

import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.Task;

public interface TaskDistributionService {

	/**
	 * Method to add task into slots
	 * @param tasks
	 * @throws AwayDayException
	 */
	public void addTasks(List<Task> tasks) throws AwayDayException;
	
	/**
	 * Method to used to initialize Day Program based on number of teams
	 * @param morningStart
	 * @param morningEnd
	 * @param eveningStart
	 * @param eveningEnd
	 * @param eveningExtraTime
	 * @param tasks
	 * @return TaskDistributionService
	 * @throws AwayDayException
	 */
	public TaskDistributionService initialize(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime, List<Task> tasks) throws AwayDayException;
}
