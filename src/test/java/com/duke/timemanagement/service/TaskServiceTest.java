package com.duke.timemanagement.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.timemanagement.util.CustomAbstractTransactionalJUnit4SpringContextTest;

public class TaskServiceTest extends CustomAbstractTransactionalJUnit4SpringContextTest {

	@Autowired
	private TaskService taskService;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
