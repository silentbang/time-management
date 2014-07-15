package com.duke.passato.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;
import com.duke.passato.util.CustomAbstractTransactionalJUnit4SpringContextTest;

public class ProjectDAOTest extends CustomAbstractTransactionalJUnit4SpringContextTest {

	@Autowired
	private ProjectDAO projectDAO;

	@Test
	public void testListProjects() {
		List<Project> projects = this.projectDAO.listProjects();
		assertEquals(1, projects.size());
		assertEquals(2, projects.get(0).getProjectId().intValue());
		assertEquals("2014/06", projects.get(0).getName());
		assertEquals("2014-06-30 23:44:50.66", projects.get(0).getCreatedDate().toString());
	}

	@Test
	public void testInsertProject() {
		Project project = new Project();
		project.setName("2014/07");

		this.projectDAO.insertProject(project);
		List<Project> projects = this.projectDAO.listProjects();

		assertEquals(2, projects.size());
		assertEquals("2014/07", projects.get(0).getName());
	}

	@Test
	public void testFindProjectById() {
		Project project = this.projectDAO.findProjectById(2);
		assertEquals(2, project.getProjectId().intValue());
		assertEquals("2014/06", project.getName());
		assertEquals("2014-06-30 23:44:50.66", project.getCreatedDate().toString());
	}

	@Test
	public void testUpdateProject() {
		Project project = this.projectDAO.findProjectById(2);
		project.setName("2015/01");

		this.projectDAO.updateProject(project);

		Project updatedProject = this.projectDAO.findProjectById(2);
		assertEquals("2015/01", updatedProject.getName());
	}

	@Test
	public void testDeleteProject() {
		Project project = this.projectDAO.findProjectById(2);
		this.projectDAO.deleteProject(project);

		List<Project> projects = this.projectDAO.listProjects();
		assertEquals(0, projects.size());
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testCalculateProjectDurationByTaskType() {
		Project project = this.projectDAO.findProjectById(2);
		List<Object> results = this.projectDAO.calculateProjectDurationByTaskType(project);
		assertEquals(4, results.size());

		Map result1 = (Map) results.get(0);
		assertEquals(17.0, result1.get(Constant.Tag.SUM_ESTIMATEDDURATION));
		assertEquals(1, result1.get(Constant.Tag.TASK_TASKTYPEID));

		Map result2 = (Map) results.get(1);
		assertEquals(7.0, result2.get(Constant.Tag.SUM_ESTIMATEDDURATION));
		assertEquals(2, result2.get(Constant.Tag.TASK_TASKTYPEID));

		Map result3 = (Map) results.get(2);
		assertEquals(5.25, result3.get(Constant.Tag.SUM_ESTIMATEDDURATION));
		assertEquals(3, result3.get(Constant.Tag.TASK_TASKTYPEID));

		Map result4 = (Map) results.get(3);
		assertEquals(14.0, result4.get(Constant.Tag.SUM_ESTIMATEDDURATION));
		assertEquals(4, result4.get(Constant.Tag.TASK_TASKTYPEID));
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testCalculateProjectDuration() {
		Project project = this.projectDAO.findProjectById(2);
		Object result = this.projectDAO.calculateProjectDuration(project);
		Assert.assertNotNull(result);

		Map resultMap = (Map) result;
		assertEquals(43.25, resultMap.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION));
		assertEquals(36.25, resultMap.get(Constant.Tag.SUM_TOTALACTUALDURATION));
		assertEquals(80.57692307692308, ((Double) resultMap.get(Constant.Tag.SUM_AVERAGEPROGRESS)).doubleValue(), Constant.DELTA);
	}
}
