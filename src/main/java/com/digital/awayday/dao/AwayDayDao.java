package com.digital.awayday.dao;

import java.util.List;

import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.Task;

public interface AwayDayDao {

	/**
	 * Method is used to load task from resource file
	 * @return List<Task>
	 * @throws AwayDayException
	 */
	List<Task> loadTasksFromResources() throws AwayDayException;
}
