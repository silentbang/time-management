package com.duke.passato.dao;

import java.util.List;

import com.duke.passato.model.Project;

public interface ProjectDAO {

	public List<Project> listProjects();

	public void insertProject(Project project);

	public Project findProjectById(Integer projectId);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public List<Object> calculateProjectDurationByTaskType(Project project);

	public Object calculateProjectDuration(Project project);
}
