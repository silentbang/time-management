package com.duke.timemanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.duke.timemanagement.model.Project;

public interface ProjectService {
	public List<Project> listProjects();

	public void insertProject(Project project);

	public Project findProjectById(Integer projectId);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public Map<String, BigDecimal> calculateProjectDurationByTaskType(Project project);
}
