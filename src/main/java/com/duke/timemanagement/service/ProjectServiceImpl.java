package com.duke.timemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

}
