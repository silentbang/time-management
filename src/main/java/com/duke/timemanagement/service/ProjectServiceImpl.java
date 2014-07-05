package com.duke.timemanagement.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.duke.timemanagement.common.Constant;
import com.duke.timemanagement.dao.ProjectDAO;
import com.duke.timemanagement.model.Project;

@Service("projectService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	@Override
	public List<Project> listProjects() {
		return this.projectDAO.listProjects();
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

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Double> calculateProjectDurationByTaskType(Project project) {
		List<Object> results = this.projectDAO.calculateProjectDurationByTaskType(project);
		Iterator<Object> iterator = results.iterator();

		Map<String, Double> durationCountMap = new HashMap<String, Double>();
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
}
