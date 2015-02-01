package com.duke.passato.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;
import com.duke.passato.service.ProjectService;
import com.duke.passato.util.CustomAbstractTransactionalJUnit4SpringContextTest;

public class ProjectServiceTest extends CustomAbstractTransactionalJUnit4SpringContextTest {

	@Autowired
	private ProjectService projectService;

	@Test
	public void testCalculateProjectDurationByTaskType() {
		Project project = this.projectService.findProjectById(2);
		Map<String, Double> durationsByTaskType = this.projectService.calculateProjectDurationByTaskType(project);
		assertEquals(17, durationsByTaskType.get("1").doubleValue(), Constant.DELTA);
		assertEquals(7, durationsByTaskType.get("2").doubleValue(), Constant.DELTA);
		assertEquals(5.25, durationsByTaskType.get("3").doubleValue(), Constant.DELTA);
		assertEquals(14, durationsByTaskType.get("4").doubleValue(), Constant.DELTA);
	}

	@Test
	public void testCalculateProjectDuration_CaseSuccess() {
		Project project = this.projectService.findProjectById(2);
		Map<String, BigDecimal> projectDurations = this.projectService.calculateProjectDuration(project);
		// In test mode, value is of type Double, not BigDecimal
		assertEquals(Double.valueOf(43.25), projectDurations.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION));
		assertEquals(Double.valueOf(36.25), projectDurations.get(Constant.Tag.SUM_TOTALACTUALDURATION));
		assertEquals(Double.valueOf(80.57692307692308), projectDurations.get(Constant.Tag.SUM_AVERAGEPROGRESS));
	}

	@Test
	public void testCalculateProjectDuration_CaseNull() {
		Project project = new Project();
		project.setProjectId(99999);
		Map<String, BigDecimal> projectDurations = this.projectService.calculateProjectDuration(project);
		assertEquals(BigDecimal.valueOf(0), projectDurations.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION));
		assertEquals(BigDecimal.valueOf(0), projectDurations.get(Constant.Tag.SUM_TOTALACTUALDURATION));
		assertEquals(BigDecimal.valueOf(0), projectDurations.get(Constant.Tag.SUM_AVERAGEPROGRESS));
	}
}
