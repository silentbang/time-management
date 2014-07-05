package com.duke.timemanagement.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnit44Runner;

import com.duke.timemanagement.common.DateUtils;
import com.duke.timemanagement.dao.TaskDAO;
import com.duke.timemanagement.model.Task;
import com.duke.timemanagement.util.CustomAbstractTransactionalJUnit4SpringContextTest;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnit44Runner.class)
public class TaskServiceTest extends CustomAbstractTransactionalJUnit4SpringContextTest {

	@Mock
	private TaskDAO taskDAO;
	@Mock
	private DateUtils dateUtils;

	@InjectMocks
	private final TaskService taskService = new TaskServiceImpl();;

	/**
	 * Also test for listTasksByProject
	 */
	@Test
	public void testListTasks_tasksOutOfDate_updateExpiryStatus() {
		Date deadline = new Date(123, 7, 21, 20, 10, 48);// 2023
		Task task = new Task();
		task.setName("New Name");
		task.setEstimatedDuration(4.5);
		task.setDeadline(deadline);
		task.setIsFinished(false);

		List<Task> originalTasks = new ArrayList<Task>();
		originalTasks.add(task);

		Mockito.when(this.dateUtils.getCurrentDate()).thenReturn(new Date(125, 1, 1));// 2025-02-01
		Mockito.when(this.taskDAO.listTasks()).thenReturn(originalTasks);

		List<Task> tasks = this.taskService.listTasks();

		assertEquals(1, tasks.size());
		assertEquals(true, tasks.get(0).getIsFinished());
	}

	@Test
	public void testListTasks_tasksNotOutOfDate_keepTaskIdle() {
		Date deadline = new Date(125, 7, 21, 20, 10, 48);// 2015
		Task task = new Task();
		task.setName("New Name 2");
		task.setEstimatedDuration(6.5);
		task.setDeadline(deadline);
		task.setIsFinished(false);

		List<Task> originalTasks = new ArrayList<Task>();
		originalTasks.add(task);

		Mockito.when(this.dateUtils.getCurrentDate()).thenReturn(new Date(124, 1, 1));// 2014-02-01
		Mockito.when(this.taskDAO.listTasks()).thenReturn(originalTasks);

		List<Task> tasks = this.taskService.listTasks();

		assertEquals(1, tasks.size());
		assertEquals(false, tasks.get(0).getIsFinished());
	}

	/**
	 * Also test updateTask(), saveTask(), autoCorrectExpiryStatus()
	 */
	@Test
	public void testInsertTask() {
		Date deadline = new Date(112, 7, 21, 20, 10, 48);
		Task task = new Task();
		task.setName("New Name 2");
		task.setEstimatedDuration(6.5);
		task.setDeadline(deadline);
		task.setIsFinished(false);

		Mockito.when(this.dateUtils.getCurrentDate()).thenReturn(new Date(114, 1, 1));// 2014-02-01

		this.taskService.insertTask(task);
		// Status is changed after performing insertion
		assertEquals(true, task.getIsFinished());
	}

}
