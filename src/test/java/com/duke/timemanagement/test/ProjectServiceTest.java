package com.duke.timemanagement.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.timemanagement.model.Project;
import com.duke.timemanagement.service.ProjectService;

public class ProjectServiceTest extends AbstractServiceTest {

	@Autowired()
	private ProjectService projectService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<Project> projects = this.projectService.listProjects();
		Assert.assertEquals(0, projects.size());

		String projectName = "Demo Project";
		Project project = new Project();
		project.setName(projectName);
		this.projectService.insertProject(project);

		projects = this.projectService.listProjects();
		Assert.assertEquals(1, projects.size());
		Assert.assertEquals(projectName, projects.get(0).getName());
	}
}
