package com.duke.timemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.duke.timemanagement.util.CustomAbstractTransactionalJUnit4SpringContextTest;

public class ProjectServiceTest extends CustomAbstractTransactionalJUnit4SpringContextTest {

	@Autowired()
	private ProjectService projectService;

}
