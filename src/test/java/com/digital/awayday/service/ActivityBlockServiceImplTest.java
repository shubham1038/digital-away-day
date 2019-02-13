package com.digital.awayday.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.digital.awayday.AppClient;
import com.digital.awayday.model.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClient.class, loader = AnnotationConfigContextLoader.class)
public class ActivityBlockServiceImplTest {

	@Autowired
	ActivityBlockService activityBlockService;
	
	@Test
	public void addTask_ok() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(60);
		Task task = new Task("test", 60);
		// when
		boolean result = block.addTask(task, true);
		// Then
		assertTrue("Result must be true", result);
	}
	
	@Test
	public void addTask_overSize() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		Task task = new Task("test", 60);
		// when
		boolean result = block.addTask(task, true);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void addTask_nullTask() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		// when
		boolean result = block.addTask(null, true);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void getAvailableSize_empy() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
	@Test
	public void getAvailableSize_half() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		block.addTask(new Task("test", 20), true);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 10", result == 10);
	}
	
	@Test
	public void getAvailableSize_full() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		block.addTask(new Task("test", 30), true);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	
	@Test
	public void getUsedSize_empy() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	@Test
	public void getUsedSize_half() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		block.addTask(new Task("test", 20), true);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 20", result == 20);
	}
	
	@Test
	public void getUsedSize_full() {
		// Given
		ActivityBlockService block = activityBlockService.activityBlockSetup(30);
		block.addTask(new Task("test", 30), true);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
}
