package com.duke.passato.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.passato.common.Constant;
import com.duke.passato.dao.TaskDAO;
import com.duke.passato.model.Task;
import com.duke.passato.util.CustomAbstractTransactionalJUnit4SpringContextTest;

public class TaskDAOTest extends CustomAbstractTransactionalJUnit4SpringContextTest {

	@Autowired
	private TaskDAO taskDAO;

	@Test
	public void testListTasks() {
		List<Task> tasks = this.taskDAO.listTasks();
		assertEquals(26, tasks.size());
		assertEquals("Record product demo - Android", tasks.get(0).getName());
		assertEquals("Hanu Test", tasks.get(25).getName());
	}

	@Test
	public void testListTasksByProject_validProjectId_success() {
		List<Task> tasksByProject = this.taskDAO.listTasksByProject(2);
		assertEquals(26, tasksByProject.size());
	}

	@Test
	public void testListTasksByProject_invalidProjectId_emptyList() {
		List<Task> tasksByProject = this.taskDAO.listTasksByProject(1000);
		assertEquals(0, tasksByProject.size());
	}

	@Test
	public void testInsertTask() {
		String taskName = "Task Name";
		Task task = new Task();
		task.setName(taskName);

		this.taskDAO.insertTask(task);

		List<Task> tasks = this.taskDAO.listTasks();
		assertEquals(27, tasks.size());
		assertEquals(taskName, tasks.get(26).getName());
	}

	@Test
	public void testFindTaskById() {
		Task task = this.taskDAO.findTaskById(20);
		assertEquals("Document time-management skills", task.getName());
		assertEquals(1, task.getEstimatedDuration().doubleValue(), Constant.DELTA);
		assertEquals(0.5, task.getActualDuration().doubleValue(), Constant.DELTA);
		assertEquals("2014-06-01 00:00:00.47", task.getDeadline().toString());
		assertEquals(100, task.getCompletedPercentage().doubleValue(), Constant.DELTA);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateTask() {
		Date deadline = new Date(115, 7, 21, 20, 10, 48);

		Task task = this.taskDAO.findTaskById(20);
		task.setName("New Name");
		task.setEstimatedDuration(4.5);
		task.setActualDuration(6.7);
		task.setDeadline(deadline);
		task.setCompletedPercentage(20.5);

		this.taskDAO.updateTask(task);

		Task updatedTask = this.taskDAO.findTaskById(20);
		assertEquals("New Name", updatedTask.getName());
		assertEquals(4.5, task.getEstimatedDuration().doubleValue(), Constant.DELTA);
		assertEquals(6.7, task.getActualDuration().doubleValue(), Constant.DELTA);
		assertEquals(deadline, task.getDeadline());
		assertEquals(20.5, task.getCompletedPercentage().doubleValue(), Constant.DELTA);
	}

	@Test
	public void testSaveTask() {
		String taskName = "Task Name";
		Task task = new Task();
		task.setName(taskName);

		this.taskDAO.saveTask(task);

		List<Task> tasks = this.taskDAO.listTasks();
		assertEquals(27, tasks.size());
		assertEquals(taskName, tasks.get(26).getName());
	}

	@Test
	public void testDeleteTask() {
		List<Task> tasks = this.taskDAO.listTasks();
		assertEquals(26, tasks.size());
		assertEquals("Document time-management skills", this.taskDAO.findTaskById(20).getName());

		Task task = this.taskDAO.findTaskById(20);
		this.taskDAO.deleteTask(task);

		List<Task> tasksAfterDeletion = this.taskDAO.listTasks();
		assertEquals(25, tasksAfterDeletion.size());
		assertEquals(null, this.taskDAO.findTaskById(20));
	}

}
