package com.duke.timemanagement.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duke.timemanagement.model.Project;

@Repository("projectDAO")
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> listProjects() {
		return this.sessionFactory.getCurrentSession().createCriteria(Project.class).addOrder(Order.desc("projectId")).list();
	}

	@Override
	public void insertProject(Project project) {
		this.sessionFactory.getCurrentSession().save(project);
	}

	@Override
	public Project findProjectById(Integer projectId) {
		return (Project) this.sessionFactory.getCurrentSession().get(Project.class, projectId);
	}

	@Override
	public void updateProject(Project project) {
		this.sessionFactory.getCurrentSession().update(project);
	}

	@Override
	public void deleteProject(Project project) {
		if (project != null) {
			this.sessionFactory.getCurrentSession().delete(project);
		}
	}

}
