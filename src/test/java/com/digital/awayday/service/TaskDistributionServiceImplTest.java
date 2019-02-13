package com.digital.awayday.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.digital.awayday.AppClient;
import com.digital.awayday.exception.AwayDayException;
import com.digital.awayday.model.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClient.class, loader = AnnotationConfigContextLoader.class)
public class TaskDistributionServiceImplTest {

	@Autowired
	TaskDistributionService taskDistributionService;
	@Test
	public void addTasks() throws AwayDayException {
		// Given
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task("prueba1", 60));
		tasks.add(new Task("prueba2", 15));
		tasks.add(new Task("prueba3", 60));
		tasks.add(new Task("prueba4", 30));
		tasks.add(new Task("prueba5", 60));
		tasks.add(new Task("prueba6", 60));
		tasks.add(new Task("prueba7", 30));
		tasks.add(new Task("aprueba8", 60));
		tasks.add(new Task("aprueba9", 60));
		tasks.add(new Task("aprueba10", 15));
		tasks.add(new Task("aprueba11", 60));
		tasks.add(new Task("aprueba12", 60));
		tasks.add(new Task("aprueba13", 60));
		tasks.add(new Task("aprueba14", 60));
		tasks.add(new Task("aprueba15", 30));
		TaskDistributionService awayDay = taskDistributionService.initialize(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0,tasks);
		
		// when
		awayDay.addTasks(tasks);
		// Then -> no exception
	}

}
