package com.digital.awayday.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digital.awayday.model.Task;

/**
 * Class for a group of tasks with a delimited size
 * @author Shubham Agarwal
 *
 */
@Service
public class ActivityBlockServiceImpl implements ActivityBlockService{

	private List<Task> tasks;

	private Integer size;

	public ActivityBlockServiceImpl() {
	}
	
	public ActivityBlockServiceImpl(Integer size) {
		this.tasks = new ArrayList<>();
		this.size = size;
	}
	
	@Override
	public ActivityBlockService activityBlockSetup(Integer size) {
		this.tasks = new ArrayList<>();
		this.size = size;
		return this;
	}
	
	/**
	 * Method to add a task if there is enough free size
	 * 
	 * @param task the task to add
	 * @return
	 */
	@Override
	public boolean addTask(Task task, boolean isMorningTask) {
		if (task != null && task.getDuration() <= this.getAvailableSize()) {
			this.tasks.add(task);
			if(!isMorningTask && task != null && this.getAvailableSize() ==0) {
				Task t = new Task("Staff Motivation Presentation",60);
				t.setStartTime(LocalTime.of(13, 00).plusMinutes(180));
				this.tasks.add(t);
			}
			return true;
		}
		return false;
	}

	@Override
	public Integer getAvailableSize() {
		return this.size - tasks.stream().mapToInt(task -> task.getDuration()).sum();
	}

	@Override
	public Integer getUsedSize() {
		return tasks.stream().mapToInt(task -> task.getDuration()).sum();
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		tasks.forEach(task -> buffer.append(task).append(System.getProperty("line.separator")));
		return buffer.toString();
	}
}