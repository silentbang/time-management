package com.duke.timemanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.duke.timemanagement.dao.TaskDAO;
import com.duke.timemanagement.model.Task;

@Service("taskService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;

	@Override
	public List<Task> listTasks() {
		return this.taskDAO.listTasks();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void insertTask(Task task) {
		task = this.autoCheckAndCorrectExpiry(task);
		this.taskDAO.insertTask(task);
	}

	@Override
	public Task findTaskById(Integer taskId) {
		Task task = this.taskDAO.findTaskById(taskId);

		// Check whether task expired & auto-correct
		Boolean needsActionBeforeChecking = this.needsAction(task);
		task = this.autoCheckAndCorrectExpiry(task);
		Boolean needsActionAfterChecking = this.needsAction(task);
		// If different between before and after then update task
		if (needsActionBeforeChecking != needsActionAfterChecking) {
			this.taskDAO.updateTask(task);
		}

		return task;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void updateTask(Task task) {
		task = this.autoCheckAndCorrectExpiry(task);
		this.taskDAO.updateTask(task);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveTask(Task task) {
		task = this.autoCheckAndCorrectExpiry(task);
		this.taskDAO.saveTask(task);
	}

	@Override
	public void deleteTask(Task task) {
		this.taskDAO.deleteTask(task);
	}

	/**
	 * Only check & apply only if task is not expired
	 */
	private Task autoCheckAndCorrectExpiry(Task task) {
		if (this.needsAction(task)) {
			Date currentDate = new Date();// Instantiate to current date-time
			Date deadline = task.getDeadline();
			if (this.isExpired(currentDate, deadline)) {
				task.setIsFinished(true);
			}
		}

		return task;
	}

	/**
	 * Check whether needs any actions, if task is already finished, keep it
	 * idle
	 */
	private boolean needsAction(Task task) {
		if (task.getIsFinished() == null) {
			return true;
		}
		else {
			return !task.getIsFinished();
		}
	}

	private boolean isExpired(Date currentDate, Date deadline) {
		return deadline != null && deadline.before(currentDate);
	}

}
