package com.duke.timemanagement.service;

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
		this.taskDAO.insertTask(task);
	}

	@Override
	public Task findTaskById(Integer taskId) {
		return this.taskDAO.findTaskById(taskId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void updateTask(Task task) {
		this.taskDAO.updateTask(task);
	}

	@Override
	public void deleteTask(Task task) {
		this.taskDAO.deleteTask(task);
	}

}
