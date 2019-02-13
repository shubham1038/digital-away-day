package com.digital.awayday.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.Task;

@Service
public class TaskDistributionServiceImpl implements TaskDistributionService{

	@Autowired
	DayProgramService dayProgramService;
	
	List<DayProgramService> programs = new ArrayList<>();
	
	/**
	 *  Method to used to initialize Day Program based on number of teams
	 */
	@Override
	public TaskDistributionService initialize(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime, List<Task> tasks) throws AwayDayException {

		for (int i = 0; i < numberOfTeams(tasks); i++) {
			programs.add(dayProgramService.initialize(morningStart, morningEnd, eveningStart, eveningEnd, eveningExtraTime));
		}
		return this;
	}
	
	/**
	 * Method to add task into slots
	 */
	@Override
	public void addTasks(List<Task> tasks) throws AwayDayException {
		Task currentTask = null;
		try {
			for (Task task : tasks) {
				currentTask = task;
				this.addSingleTask(task);
			}
		} catch (Exception e) {
			throw new AwayDayException("AwayDay.addTasks: There was a problem inserting task (" + currentTask.getName() + ")");
		}
	}

	/**
	 * Method is used to calculate number of team can be formed in given tasks.
	 * @param tasks
	 * @return no of teams
	 * @throws AwayDayException
	 */
	private double numberOfTeams(List<Task> tasks) throws AwayDayException {

		Integer tasksTime = tasks.stream().mapToInt(task -> task.getDuration()).sum();
		return Math.floor(tasksTime/360);

	}
	
	/**
	 * Method to add a task to one of the programs
	 * @param task Task to add
	 * @return
	 */
	private boolean addSingleTask(Task task) {
		boolean result = false;
		Iterator<DayProgramService> iterator = programs.iterator();
		while (result != true && iterator.hasNext()) {
			result = ((DayProgramService) iterator.next()).insertMorningTask(task);
		}
		iterator = programs.iterator();
		while (result != true && iterator.hasNext()) {
			result = ((DayProgramService) iterator.next()).insertEveningTask(task);
		}
		return result;
	}

	public List<DayProgramService> getPrograms() {
		return programs;
	}

	public void setPrograms(List<DayProgramService> programs) {
		this.programs = programs;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer("");
		Integer count = 1;
		for (DayProgramService dayProgram : programs) {
			buffer
			.append("Team " + count + ":")
			.append(System.getProperty("line.separator"))
			.append(dayProgram)
			.append(System.getProperty("line.separator"));
			count ++;
		}
		return buffer.append(System.getProperty("line.separator")).toString();
	}
}
