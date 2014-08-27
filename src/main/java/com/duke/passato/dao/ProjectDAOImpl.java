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

	private static final String SQL_PROJECT_COUNT = "SELECT COUNT(*) FROM Project";
	private static final String SQL_DURATION_BY_TASK_TYPE = "SELECT \"taskTypeId\", SUM(\"estimatedDuration\") " + "FROM \"task\" "
			+ "WHERE \"projectId\" = :projectId GROUP BY \"taskTypeId\" ORDER BY \"taskTypeId\" ASC";
	private static final String SQL_DURATION_BY_PROJECT = "SELECT SUM(\"estimatedDuration\") as \"totalEstimatedDuration\", " + " SUM(\"actualDuration\") as \"totalActualDuration\", "
			+ " SUM(\"completedPercentage\") / count(\"taskId\") as \"averageProgress\" " + " FROM \"task\" " + " WHERE \"projectId\" = :projectId";

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> listProjects() {
		return this.sessionFactory.getCurrentSession().createCriteria(Project.class).addOrder(Order.desc("projectId")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> listProject(Integer pageNumber, Integer maxResultsPerPage) {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(Project.class).addOrder(Order.desc("projectId"));
		int actualPageNumber = pageNumber - 1;
		Integer firstRowNumber = actualPageNumber * maxResultsPerPage;
		c.setFirstResult(firstRowNumber);
		c.setMaxResults(maxResultsPerPage);

		return c.list();
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
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(SQL_DURATION_BY_TASK_TYPE).setParameter("projectId", project.getProjectId())
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Object> result = query.list();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object calculateProjectDuration(Project project) {
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(SQL_DURATION_BY_PROJECT).setParameter("projectId", project.getProjectId())
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Object> result = query.list();

		return result.get(0);
	}

	@Override
	public Long getProjectCount() {
		return (Long) this.sessionFactory.getCurrentSession().createQuery(SQL_PROJECT_COUNT).uniqueResult();
	}

}
