package com.digital.awayday.service;

import com.digital.awayday.model.Task;

public interface DayProgramService {

	/**
	 * Method is used to insert task into Morning slot
	 * @param task
	 * @return boolean
	 */
	public boolean insertMorningTask(Task task);
	
	/**
	 *  Method is used to insert task into Evening slot
	 * @param task
	 * @return boolean
	 */
	public boolean insertEveningTask(Task task);
	
	/**
	 * Method to get the min day program duration in minutes
	 * @return Integer
	 */
	public Integer getMinDuration();
	
	/**
	 * Method to get the max day program duration in minutes
	 * @return Integer
	 */ 
	public Integer getMaxDuration();
	
}
