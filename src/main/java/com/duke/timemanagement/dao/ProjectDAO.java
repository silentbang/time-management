package com.duke.timemanagement.dao;

import java.util.List;

import com.duke.timemanagement.model.Project;

public interface ProjectDAO {

	public List<Project> listProjects();

	public void insertProject(Project project);

	public Project findProjectById(Integer projectId);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public List<Object> calculateProjectDurationByTaskType(Project project);
}
