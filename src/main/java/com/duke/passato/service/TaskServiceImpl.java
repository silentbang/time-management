package com.duke.passato.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.duke.passato.bean.PlanningBean;
import com.duke.passato.common.DateUtils;
import com.duke.passato.dao.TaskDAO;
import com.duke.passato.model.Task;

@Service("taskService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private DateUtils dateUtils;

	@Override
	public List<Task> listTasks() {
		List<Task> tasks = this.taskDAO.listTasks();
		// Auto correct expiry status & save tasks
		tasks = this.autoCorrectExpiryStatusAndSaveTasks(tasks);

		return tasks;
	}

	@Override
	public List<Task> listTasksByProject(Integer projectId) {
		List<Task> tasks = this.taskDAO.listTasksByProject(projectId);
		// Auto correct expiry status & save tasks
		tasks = this.autoCorrectExpiryStatusAndSaveTasks(tasks);

		return tasks;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void insertTask(Task task) {
		// Auto correct expiry status before inserting
		task = this.autoCorrectExpiryStatus(task);
		this.taskDAO.insertTask(task);
	}

	@Override
	public Task findTaskById(Integer taskId) {
		Task task = this.taskDAO.findTaskById(taskId);
		// Auto correct expiry status & save task
		task = this.autoCorrectExpiryStatusAndSaveTask(task);

		return task;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void updateTask(Task task) {
		// Auto correct expiry status before updating
		task = this.autoCorrectExpiryStatus(task);
		this.taskDAO.updateTask(task);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveTask(Task task) {
		// Auto correct expiry status before saving
		task = this.autoCorrectExpiryStatus(task);
		this.taskDAO.saveTask(task);
	}

	@Override
	public void deleteTask(Task task) {
		this.taskDAO.deleteTask(task);
	}

	@Override
	public PlanningBean listPlanningDayByProject(Integer projectId) {
		// Already verify expiry status
		List<Task> tasks = this.listTasksByProject(projectId);

		// Populate tasks into PlanningBean
		PlanningBean planningBean = new PlanningBean(tasks);

		return planningBean;
	}

	/**
	 * Auto-correct and save multiple tasks
	 */
	private List<Task> autoCorrectExpiryStatusAndSaveTasks(List<Task> tasks) {
		List<Task> updatedTasks = new ArrayList<Task>();
		for (Task task : tasks) {
			this.autoCorrectExpiryStatusAndSaveTask(task);
			updatedTasks.add(task);
		}

		return updatedTasks;
	}

	/**
	 * Auto-correct and save task
	 */
	private Task autoCorrectExpiryStatusAndSaveTask(Task task) {
		// If not need action then return (task already finished)
		Boolean needsActionBeforeChecking = this.needsAction(task);
		if (needsActionBeforeChecking == false) {
			return task;
		}

		task = this.autoCorrectExpiryStatus(task);
		Boolean needsActionAfterChecking = this.needsAction(task);

		// If different between statuses before and after checking then update
		if (needsActionBeforeChecking != needsActionAfterChecking) {
			this.taskDAO.updateTask(task);
		}
		return task;
	}

	/**
	 * Auto correct expiry status (only check & apply only if task is not
	 * expired)
	 */
	private Task autoCorrectExpiryStatus(Task task) {
		if (this.needsActionAndIsExpired(task)) {
			task.setIsFinished(true);
		}

		return task;
	}

	/**
	 * Check whether needs action and already expired
	 */
	private boolean needsActionAndIsExpired(Task task) {
		Date deadline = task.getDeadline();

		return this.needsAction(task) && this.isExpired(deadline);
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

	/**
	 * Check whether currentDate is after deadline
	 */
	private boolean isExpired(Date deadline) {
		Date currentDate = this.dateUtils.getCurrentDate();
		return deadline != null && deadline.before(currentDate);
	}
}
