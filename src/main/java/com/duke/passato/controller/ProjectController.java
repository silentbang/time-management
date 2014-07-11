package com.duke.passato.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.duke.passato.bean.ProjectBean;
import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;
import com.duke.passato.service.ProjectService;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listProjects() {
		ModelAndView mav = new ModelAndView();
		List<Project> projects = this.projectService.listProjects();
		mav.addObject("projects", this.prepareListOfBeans(projects));
		mav.setViewName("projectList");

		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addProject() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", new ProjectBean());
		mav.setViewName("projectAdd");

		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveProject(@ModelAttribute("project") ProjectBean projectBean, BindingResult result) {
		Project project = this.prepareModel(projectBean);
		this.projectService.insertProject(project);
		return new ModelAndView("redirect:/projects");
	}

	@RequestMapping(value = "/update/{projectId}", method = RequestMethod.GET)
	public ModelAndView updateProject(@PathVariable Integer projectId) {
		Project project = this.projectService.findProjectById(projectId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", project);
		mav.setViewName("projectUpdate");

		return mav;
	}

	@RequestMapping(value = "/update/{projectId}", method = RequestMethod.POST)
	public ModelAndView updateProject(@ModelAttribute Project project, @PathVariable Integer projectId) {
		this.projectService.updateProject(project);

		return new ModelAndView("redirect:/projects");
	}

	@RequestMapping(value = "/delete/{projectId}", method = RequestMethod.GET)
	public ModelAndView deleteProject(@PathVariable Integer projectId) {
		Project project = this.projectService.findProjectById(projectId);
		this.projectService.deleteProject(project);

		return new ModelAndView("redirect:/projects");
	}

	private List<ProjectBean> prepareListOfBeans(List<Project> projects) {
		List<ProjectBean> beans = null;
		if (projects != null && !projects.isEmpty()) {
			beans = new ArrayList<ProjectBean>();
			ProjectBean bean = null;

			for (Project project : projects) {
				// Query for estimated & actual durations
				Map<String, BigDecimal> projectDurations = this.projectService.calculateProjectDuration(project);

				bean = new ProjectBean();
				bean.setProjectId(project.getProjectId());
				bean.setName(project.getName());
				bean.setCreatedDate(project.getCreatedDate());
				bean.setTotalEstimatedDuration(projectDurations.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION).doubleValue());
				bean.setTotalActualDuration(projectDurations.get(Constant.Tag.SUM_TOTALACTUALDURATION).doubleValue());

				beans.add(bean);
			}
		}

		return beans;
	}

	private Project prepareModel(ProjectBean projectBean) {
		Project project = new Project();
		project.setProjectId(projectBean.getProjectId());
		project.setName(projectBean.getName());

		return project;
	}

}
