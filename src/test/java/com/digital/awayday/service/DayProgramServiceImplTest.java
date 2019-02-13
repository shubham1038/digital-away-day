package com.digital.awayday.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.digital.awayday.AppClient;
import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClient.class, loader = AnnotationConfigContextLoader.class)
public class DayProgramServiceImplTest {

	@Test
	public void insertMorningTask_ok() throws AwayDayException {
		// Given
		
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		Task task = new Task("test", 60);
		// when
		boolean result = dayProgram.insertMorningTask(task);
		// Then
		assertTrue("Result must be true", result);
	}
	
	@Test
	public void insertMorningTask_noSpace() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		Task task = new Task("test", 60);
		// when
		boolean result = dayProgram.insertMorningTask(task);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void insertMorningTask_nullTask() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		// when
		boolean result = dayProgram.insertMorningTask(null);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test(expected = AwayDayException.class)
	public void insertMorningTask_constructorFail() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(8, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		// when
		dayProgram.insertMorningTask(null);
		// Then -> exception
	}	
	
	@Test
	public void insertEveningTask_ok() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		Task task = new Task("test", 60);
		// when
		boolean result = dayProgram.insertEveningTask(task);
		// Then
		assertTrue("Result must be true", result);
	}
	
	@Test
	public void insertEveningTask_noSpace() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		Task task = new Task("test", 60);
		// when
		boolean result = dayProgram.insertEveningTask(task);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void insertEveningTask_nullTask() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		// when
		boolean result = dayProgram.insertEveningTask(null);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test(expected = AwayDayException.class)
	public void insertEveningTask_constructorFail() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(12, 00),
				0);
		// when
		dayProgram.insertEveningTask(null);
		// Then -> exception
	}
	
	@Test
	public void getMinDuration_ok() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getMinDuration_ok_extra() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				10);
		// when
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 50", result == 50);
	}
	
	@Test
	public void getMinDuration_oneTask() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		dayProgram.insertMorningTask(new Task("test", 30));
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
	@Test
	public void getMaxDuration_ok() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		Integer result = dayProgram.getMaxDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getMaxDuration_ok_extra() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				10);
		// when
		Integer result = dayProgram.getMaxDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getMaxDuration_oneTask() throws AwayDayException {
		// Given
		DayProgramService dayProgram = new DayProgramServiceImpl(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		dayProgram.insertMorningTask(new Task("test", 30));
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
}
