package com.digital.awayday.service;

import com.digital.awayday.model.Task;

public interface ActivityBlockService {

	/**
	 * Method is used to add task in morning or evening slot
	 * @param task
	 * @param isMorningTask
	 * @return boolean
	 */
	public boolean addTask(Task task, boolean isMorningTask);
	
	/**
	 * Method is used to calculate remaining time in any slots (morning or evening)
	 * @return Integer
	 */
	public Integer getAvailableSize();
	
	/**
	 * Method is used to calculate used time in any slots (morning or evening)
	 * @return Integer
	 */
	public Integer getUsedSize();
	
}
