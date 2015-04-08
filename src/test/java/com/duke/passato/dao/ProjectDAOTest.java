package com.duke.passato.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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
		assertEquals(7, projects.size());
		assertEquals(8, projects.get(0).getProjectId().intValue());
		assertEquals("2014/12", projects.get(0).getName());
		assertEquals("2014-12-30 23:44:50.66", projects.get(0).getCreatedDate().toString());
	}

	@Test
	public void testListRecentProjects() {
		List<Project> recentProjects = this.projectDAO.listRecentProjects(2);
		assertEquals(2, recentProjects.size());
		assertEquals("2014/12", recentProjects.get(0).getName());
		assertEquals("2014/11", recentProjects.get(1).getName());
	}

	@Test
	public void testListProjects_firstPage() {
		List<Project> projects = this.projectDAO.listProject(1, 2);
		assertEquals(2, projects.size());
		assertEquals(8, projects.get(0).getProjectId().intValue());
		assertEquals(7, projects.get(1).getProjectId().intValue());
		assertEquals("2014/12", projects.get(0).getName());
		assertEquals("2014-12-30 23:44:50.66", projects.get(0).getCreatedDate().toString());
		assertEquals("2014/11", projects.get(1).getName());
		assertEquals("2014-11-30 23:44:50.66", projects.get(1).getCreatedDate().toString());
	}

	@Test
	public void testListProjects_secondPage() {
		List<Project> projects = this.projectDAO.listProject(2, 2);
		assertEquals(2, projects.size());
		assertEquals(6, projects.get(0).getProjectId().intValue());
		assertEquals(5, projects.get(1).getProjectId().intValue());
		assertEquals("2014/10", projects.get(0).getName());
		assertEquals("2014-10-30 23:44:50.66", projects.get(0).getCreatedDate().toString());
		assertEquals("2014/09", projects.get(1).getName());
		assertEquals("2014-09-30 23:44:50.66", projects.get(1).getCreatedDate().toString());
	}

	@Test
	public void testListProjectsWithPaging_lastPage() {
		List<Project> projects = this.projectDAO.listProject(4, 2);
		assertEquals(1, projects.size());
		assertEquals(2, projects.get(0).getProjectId().intValue());
		assertEquals("2014/06", projects.get(0).getName());
		assertEquals("2014-06-30 23:44:50.66", projects.get(0).getCreatedDate().toString());
	}

	@Test
	public void testInsertProject() {
		Project project = new Project();
		project.setName("2014/01");

		this.projectDAO.insertProject(project);
		List<Project> projects = this.projectDAO.listProjects();

		assertEquals(8, projects.size());
		assertEquals("2014/12", projects.get(0).getName());
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
	public void testDeleteProject_CaseNotNull() {
		Project project = this.projectDAO.findProjectById(2);
		this.projectDAO.deleteProject(project);

		List<Project> projects = this.projectDAO.listProjects();
		assertEquals(6, projects.size());
	}

	@Test
	public void testDeleteProject_CaseNull() {
		this.projectDAO.deleteProject(null);

		List<Project> projects = this.projectDAO.listProjects();
		assertEquals(7, projects.size());
	}

	@Test
	public void testCalculateAverageProgress() {
		BigDecimal averageProgress = this.projectDAO.calculateAverageProgress();
		assertEquals(BigDecimal.valueOf(80.57692307692308), averageProgress);
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

	@SuppressWarnings("rawtypes")
	@Test
	public void testCalculateProjectDuration_CaseNullProject() {
		// Project project = this.projectDAO.findProjectById(2);
		//
		Project unknownProject = new Project();
		// BeanUtils.copyProperties(project, unknownProject);
		unknownProject.setProjectId(99999);

		Object result = this.projectDAO.calculateProjectDuration(unknownProject);
		Assert.assertNotNull(result);

		Map resultMap = (Map) result;
		assertEquals(null, resultMap.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION));
		assertEquals(null, resultMap.get(Constant.Tag.SUM_TOTALACTUALDURATION));
		assertEquals(null, resultMap.get(Constant.Tag.SUM_AVERAGEPROGRESS));
	}

	@Test
	public void testGetProjectCount() {
		assertEquals(Long.valueOf(7), this.projectDAO.getProjectCount());
	}
}
