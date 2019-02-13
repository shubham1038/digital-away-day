package com.digital.awayday.service;

import java.time.Duration;
import java.time.LocalTime;

import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.ExtraTimeBlock;
import com.digital.awayday.model.Task;

/**
 * Service to generate away Day Program
 * @author Shubham Agarwal
 *
 */
public class DayProgramServiceImpl implements DayProgramService{

	private LocalTime morningStart;
	private ActivityBlockService morning;

	private LocalTime eveningStart;
	private ExtraTimeBlock evening;
	
	public DayProgramServiceImpl(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime) throws AwayDayException {

		this.checkDayProgram(morningStart, morningEnd, eveningStart, eveningEnd);

		this.morningStart = morningStart;
		this.morning = new ActivityBlockServiceImpl(getBlockSize(morningStart, morningEnd));
		this.eveningStart = eveningStart;
		this.evening = new ExtraTimeBlock(getBlockSize(eveningStart, eveningEnd), eveningExtraTime);
	}
	
	/**
	 * Method to insert a task in morning block
	 * 
	 * @param task the task to insert
	 * @return true if inserted
	 */
	@Override
	public boolean insertMorningTask(Task task) {
		if (task != null){
			task.setStartTime(this.morningStart.plusMinutes(morning.getUsedSize()));
			return this.morning.addTask(task, true);
		}
		return false;
	}

	/**
	 * Method to insert a task in evening block
	 * 
	 * @param task the task to insert
	 * @return true if inserted
	 */
	@Override
	public boolean insertEveningTask(Task task) {
		
		if (task != null){
			task.setStartTime(this.eveningStart.plusMinutes(evening.getUsedSize()));
			return this.evening.addTask(task, false);
		}
		return false;
	}
	
	/**
	 * Method to get the min day program duration in minutes
	 * 
	 * @return Day program min duration (minutes)
	 */
	@Override
	public Integer getMinDuration() {

		return this.morning.getAvailableSize() + this.evening.getAvailableSize() - this.evening.getExtraTime();
	}

	/**
	 * Method to get the max day program duration in minutes
	 * 
	 * @return Day program max duration (minutes)
	 */
	@Override
	public Integer getMaxDuration() {

		return this.morning.getAvailableSize() + this.evening.getAvailableSize();
	}

	/**
	 * Method to get block size based in start and end time
	 *  
	 * @param start the LocalTime when this block starts 
	 * @param end the LocalTime when this block ends
	 * @return block size in minutes
	 * @throws Exception
	 */
	private Integer getBlockSize(LocalTime start, LocalTime end) throws AwayDayException {

		Duration duration = Duration.between(start, end);
		if (duration.getSeconds() > 0) {
			return (int) (duration.getSeconds() / 60);
		}
		throw new AwayDayException("DayProgram.getBlockSize: End time must be after start time");
	}
	
	private boolean checkDayProgram(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart,
			LocalTime eveningEnd) throws AwayDayException {

		if (morningStart != null && eveningStart != null && eveningStart != null && eveningEnd != null
				&& eveningEnd.isAfter(morningStart)) {
			return true;
		} else {
			throw new AwayDayException("DayProgram.checkDayProgram: There was a problem creating the program");
		}
	}

	public ActivityBlockService getMorning() {
		return morning;
	}

	public void setMorning(ActivityBlockService morning) {
		this.morning = morning;
	}

	public ExtraTimeBlock getEvening() {
		return evening;
	}

	public void setEvening(ExtraTimeBlock evening) {
		this.evening = evening;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer.append(morning);
		buffer.append(evening);
		return buffer.toString();
	}
}
