package com.duke.passato.service;

import java.util.List;

import com.duke.passato.bean.PlanningBean;
import com.duke.passato.model.Task;

public interface TaskService {
	public List<Task> listTasks();

	public List<Task> listTasksByProject(Integer projectId);

	public void insertTask(Task task);

	public Task findTaskById(Integer taskId);

	public void updateTask(Task task);

	public void saveTask(Task task);

	public void deleteTask(Task task);

	public PlanningBean listPlanningDayByProject(Integer projectId);
}
