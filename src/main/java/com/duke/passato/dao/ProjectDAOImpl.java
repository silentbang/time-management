package com.duke.passato.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duke.passato.model.Project;

@Repository("projectDAO")
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String SQL_DURATION_BY_TASK_TYPE = "SELECT \"taskTypeId\", SUM(\"estimatedDuration\") " + "FROM \"task\" "
			+ "WHERE \"projectId\" = :projectId GROUP BY \"taskTypeId\" ORDER BY \"taskTypeId\" ASC";
	private final String SQL_DURATION_BY_PROJECT = "SELECT SUM(\"estimatedDuration\") as \"totalEstimatedDuration\", SUM(\"actualDuration\") as \"totalActualDuration\" FROM \"task\" WHERE \"projectId\" = :projectId";

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> calculateProjectDurationByTaskType(Project project) {
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(this.SQL_DURATION_BY_TASK_TYPE).setParameter("projectId", project.getProjectId())
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Object> result = query.list();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object calculateProjectDuration(Project project) {
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(this.SQL_DURATION_BY_PROJECT).setParameter("projectId", project.getProjectId())
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Object> result = query.list();

		return (result.isEmpty() ? null : result.get(0));
	}

}