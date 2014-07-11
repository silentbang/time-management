package com.duke.passato.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duke.passato.model.Task;

@Repository("taskDAO")
@Transactional
public class TaskDAOImpl implements TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTasks() {
		return this.sessionFactory.getCurrentSession().createCriteria(Task.class).addOrder(Order.asc("taskId")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTasksByProject(Integer projectId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("project.projectId", projectId.intValue()));

		return criteria.list();
	}

	@Override
	public void insertTask(Task task) {
		this.sessionFactory.getCurrentSession().save(task);
	}

	@Override
	public Task findTaskById(Integer taskId) {
		return (Task) this.sessionFactory.getCurrentSession().get(Task.class, taskId);
	}

	@Override
	public void updateTask(Task task) {
		this.sessionFactory.getCurrentSession().update(task);
	}

	@Override
	public void saveTask(Task task) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(task);
	}

	@Override
	public void deleteTask(Task task) {
		this.sessionFactory.getCurrentSession().delete(task);
	}

}
