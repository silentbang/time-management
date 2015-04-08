package com.duke.passato.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.duke.passato.model.Project;

public interface ProjectService {
	public List<Project> listProjects();

	public List<Project> listRecentProjects(int number);

	public List<Project> listProject(Integer pageNumber, Integer maxResultsPerPage);

	public void insertProject(Project project);

	public Project findProjectById(Integer projectId);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public Double calculateAverageProgress();

	public Map<String, Double> calculateProjectDurationByTaskType(Project project);

	public Map<String, BigDecimal> calculateProjectDuration(Project project);

	public Long getProjectCount();
}
