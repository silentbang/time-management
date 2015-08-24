package com.duke.passato.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.duke.passato.bean.AveragePercentageChartBean;
import com.duke.passato.bean.DurationChartBean;
import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;
import com.duke.passato.service.ProjectService;

@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {
	private static final String SLASH = "/";
	private static final String HYPHEN = "-";
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView drawCharts(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("charts");
		return mav;
	}

	@RequestMapping(value = "/duration", method = RequestMethod.GET)
	public @ResponseBody List<DurationChartBean> durationChart(){
		List<DurationChartBean> durationChartBeans = new ArrayList<DurationChartBean>();
		List<Project> projects = this.projectService.listProjects();
		for (Project project : projects){
			Map<String, BigDecimal> projectDurations = this.projectService.calculateProjectDuration(project);
			
			String projectName = project.getName().replace(SLASH, HYPHEN);
			Double estimatedDuration = projectDurations.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION).doubleValue();
			Double actualDuration = projectDurations.get(Constant.Tag.SUM_TOTALACTUALDURATION).doubleValue();
			DurationChartBean durationChartBean = new DurationChartBean(projectName, estimatedDuration, actualDuration);
			durationChartBeans.add(durationChartBean);
		}
		
		return durationChartBeans;
	}
	
	@RequestMapping(value = "/averagePercentage", method = RequestMethod.GET)
	public @ResponseBody List<AveragePercentageChartBean> completedPercentageChart(){
		List<AveragePercentageChartBean> chartBeans = new ArrayList<AveragePercentageChartBean>();
		List<Project> projects = this.projectService.listProjects();
		for (Project project : projects){
			Map<String, BigDecimal> projectDurations = this.projectService.calculateProjectDuration(project);
			
			String projectName = project.getName().replace(SLASH, HYPHEN);
			BigDecimal averagePercentageBigDecimal = projectDurations.get(Constant.Tag.SUM_AVERAGEPROGRESS);
			Double averagePercentage = averagePercentageBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
			AveragePercentageChartBean durationChartBean = new AveragePercentageChartBean(projectName, averagePercentage);
			chartBeans.add(durationChartBean);
		}
		
		return chartBeans;
	}
}
