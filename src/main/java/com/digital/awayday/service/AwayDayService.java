package com.digital.awayday.service;

import com.digital.awayday.exception.AwayDayException;

public interface AwayDayService {

	/**
	 * Method is used to arrange task into slots
	 * @return TaskDistributionService
	 * @throws AwayDayException
	 */
	public TaskDistributionService createActivities() throws AwayDayException;
}
