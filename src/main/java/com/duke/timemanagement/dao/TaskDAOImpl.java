package com.duke.timemanagement.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.duke.timemanagement.model.Task;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTasks() {
		return this.sessionFactory.getCurrentSession().createCriteria(Task.class).list();
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
	public void deleteTask(Task task) {
		this.sessionFactory.getCurrentSession().delete(task);
	}

}
