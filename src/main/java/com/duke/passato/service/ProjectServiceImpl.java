package com.duke.passato.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.duke.passato.common.Constant;
import com.duke.passato.common.TaskType;
import com.duke.passato.dao.ProjectDAO;
import com.duke.passato.model.Project;

@Service("projectService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	@Override
	public List<Project> listProjects() {
		return this.projectDAO.listProjects();
	}

	@Override
	public List<Project> listRecentProjects(int number) {
		return this.projectDAO.listRecentProjects(number);
	}

	@Override
	public List<Project> listProject(Integer pageNumber, Integer maxResultsPerPage) {
		return this.projectDAO.listProject(pageNumber, maxResultsPerPage);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void insertProject(Project project) {
		this.projectDAO.insertProject(project);
	}

	@Override
	public Project findProjectById(Integer projectId) {
		return this.projectDAO.findProjectById(projectId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void updateProject(Project project) {
		this.projectDAO.updateProject(project);
	}

	@Override
	public void deleteProject(Project project) {
		this.projectDAO.deleteProject(project);
	}

	@Override
	public Double calculateAverageProgress() {
		return this.projectDAO.calculateAverageProgress().doubleValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Double> calculateProjectDurationByTaskType(Project project) {
		List<Object> results = this.projectDAO.calculateProjectDurationByTaskType(project);
		Iterator<Object> iterator = results.iterator();

		// Initialize all sums to 0
		Map<String, Double> durationCountMap = new HashMap<String, Double>();
		for (TaskType taskType : TaskType.values()) {
			durationCountMap.put(Integer.toString(taskType.getValue()), Double.valueOf(0));
		}

		while (iterator.hasNext()) {
			Map row = (Map) iterator.next();
			// Extract information
			int taskTypeId;
			try {
				// Web environment
				taskTypeId = (Integer) row.get(Constant.Tag.TASK_TASKTYPEID);
			}
			catch (ClassCastException e) {
				// Test environment
				taskTypeId = (Short) row.get(Constant.Tag.TASK_TASKTYPEID);
			}
			// Test environment
			Double sum = (Double) row.get(Constant.Tag.SUM_ESTIMATEDDURATION);
			if (sum == null) {// Web environment
				sum = ((BigDecimal) row.get(Constant.Tag.SUM)).doubleValue();
			}

			durationCountMap.put(Integer.toString(taskTypeId), sum);
		}

		return durationCountMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BigDecimal> calculateProjectDuration(Project project) {
		Map<String, BigDecimal> projectDurations = (Map<String, BigDecimal>) this.projectDAO.calculateProjectDuration(project);
		// If null then replace by zero
		if (projectDurations.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION) == null) {
			projectDurations.put(Constant.Tag.SUM_TOTALESTIMATEDDURATION, BigDecimal.valueOf(0));
		}
		if (projectDurations.get(Constant.Tag.SUM_TOTALACTUALDURATION) == null) {
			projectDurations.put(Constant.Tag.SUM_TOTALACTUALDURATION, BigDecimal.valueOf(0));
		}
		if (projectDurations.get(Constant.Tag.SUM_AVERAGEPROGRESS) == null) {
			projectDurations.put(Constant.Tag.SUM_AVERAGEPROGRESS, BigDecimal.valueOf(0));
		}

		return projectDurations;
	}

	@Override
	public Long getProjectCount() {
		return this.projectDAO.getProjectCount();
	}

}
