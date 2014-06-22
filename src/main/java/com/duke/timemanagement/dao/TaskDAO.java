package com.duke.timemanagement.dao;

import java.util.List;

import com.duke.timemanagement.model.Task;

public interface TaskDAO {
	public List<Task> listTasks();

	public void insertTask(Task task);

	public Task findTaskById(Integer taskId);

	public void updateTask(Task task);

	public void saveTask(Task task);

	public void deleteTask(Task task);
}
