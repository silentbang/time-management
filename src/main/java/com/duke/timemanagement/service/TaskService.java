package com.duke.timemanagement.service;

import java.util.List;

import com.duke.timemanagement.model.Task;

public interface TaskService {
	public List<Task> listTasks();

	public void insertTask(Task task);

	public Task findTaskById(Integer taskId);

	public void updateTask(Task task);

	public void saveTask(Task task);

	public void deleteTask(Task task);
}
