package com.digital.awayday.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.file.FileUtils;
import com.digital.awayday.model.Task;

/**
 * Class is used to load the tasks
 * @author Shubham Agarwal
 *
 */
@Repository
public class AwayDayDaoImpl implements AwayDayDao{

	static String INPUT = "input.txt";
	@Override
	public List<Task> loadTasksFromResources() throws AwayDayException {
		//Here we are loading task from "input.txt".
		//we can also connect Database to load tasks
		FileUtils fileUtils = new FileUtils();
	   return fileUtils.loadTasksFromResources(INPUT);
	}

}
