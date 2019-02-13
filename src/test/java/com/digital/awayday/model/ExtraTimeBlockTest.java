package com.digital.awayday.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.digital.awayday.model.ExtraTimeBlock;
import com.digital.awayday.model.Task;

public class ExtraTimeBlockTest {

	@Test
	public void addTask_ok() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(60, 10);
		Task task = new Task("test", 60);
		// when
		boolean result = block.addTask(task, false);
		// Then
		assertTrue("Result must be true", result);
	}
	
	@Test
	public void addTask_overSize() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		Task task = new Task("test", 60);
		// when
		boolean result = block.addTask(task, false);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void addTask_nullTask() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		// when
		boolean result = block.addTask(null, false);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void getAvailableSize_empy() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
	@Test
	public void getAvailableSize_half() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		block.addTask(new Task("test", 20), false);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 10", result == 10);
	}
	
	@Test
	public void getAvailableSize_full() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(90, 10);
		block.addTask(new Task("test", 30), false);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	
	@Test
	public void getUsedSize_empy() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	@Test
	public void getUsedSize_half() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		block.addTask(new Task("test", 20), false);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 20", result == 20);
	}
	
	@Test
	public void getUsedSize_full() {
		// Given
		ExtraTimeBlock block = new ExtraTimeBlock(30, 10);
		block.addTask(new Task("test", 30), false);
		// when
		Integer result = block.getUsedSize();
		// Then
		//This will include "staff motivatio task of 60 mins"
		assertTrue("Result must be 90", result == 90);
	}
	
}
