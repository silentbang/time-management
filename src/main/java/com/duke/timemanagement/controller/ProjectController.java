package com.duke.timemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.duke.timemanagement.bean.ProjectBean;
import com.duke.timemanagement.model.Project;
import com.duke.timemanagement.service.ProjectService;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listProjects() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("projects", this.prepareListOfBeans(this.projectService.listProjects()));
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
		return new ModelAndView("redirect:/projects/add");
	}

	private List<ProjectBean> prepareListOfBeans(List<Project> projects) {
		List<ProjectBean> beans = null;
		if (projects != null && !projects.isEmpty()) {
			beans = new ArrayList<ProjectBean>();
			ProjectBean bean = null;

			for (Project project : projects) {
				bean = new ProjectBean();
				bean.setProjectId(project.getProjectId());
				bean.setName(project.getName());
				bean.setCreatedDate(project.getCreatedDate());

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
