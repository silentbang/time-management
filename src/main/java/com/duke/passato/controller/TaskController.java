package com.duke.passato.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duke.passato.bean.PlanningBean;
import com.duke.passato.bean.ProjectBean;
import com.duke.passato.bean.TaskBean;
import com.duke.passato.common.DateUtils;
import com.duke.passato.common.Message;
import com.duke.passato.common.MessageType;
import com.duke.passato.common.TaskType;
import com.duke.passato.common.UIUtils;
import com.duke.passato.comparator.TaskComparator;
import com.duke.passato.model.Project;
import com.duke.passato.model.Task;
import com.duke.passato.service.ProjectService;
import com.duke.passato.service.TaskService;

@Controller
@RequestMapping(value = "/tasks")
public class TaskController extends GenericController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DateUtils dateUtils;

	@RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
	public ModelAndView updateTaskMatrix(@PathVariable Integer projectId) {
		TaskBean taskBean = new TaskBean();
		taskBean.setProjectId(projectId);

		ModelAndView mav = new ModelAndView();
		mav.addObject("task", taskBean);
		mav = this.prepareTaskListView(mav, projectId);

		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTask(@Valid @ModelAttribute("task") TaskBean taskBean, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("task", taskBean);
			mav = this.prepareTaskListView(mav, taskBean.getProjectId());
			return mav;
		}
		else {
			Task task = taskBean.transformIntoModel();
			// Set project from here (as Bean should not access service)
			task.setProject(this.projectService.findProjectById(taskBean.getProjectId()));

			this.taskService.saveTask(task);

			this.postSingleMessage(redirectAttributes, new Message(MessageType.SUCCESS, "success.task.save", task.getName()));

			return new ModelAndView("redirect:/tasks/" + task.getProject().getProjectId());
		}
	}

	@RequestMapping(value = "/update/{taskId}", method = RequestMethod.POST)
	@ResponseBody
	public TaskBean updateTask(@PathVariable Integer taskId, RedirectAttributes redirectAttributes) {
		Task task = this.taskService.findTaskById(taskId);
		TaskBean taskBean = new TaskBean(task);

		this.postSingleMessage(redirectAttributes, new Message(MessageType.SUCCESS, "success.task.update", task.getName()));

		return taskBean;
	}

	// TODO Use JSON
	@RequestMapping(value = "/delete/{taskId}", method = RequestMethod.GET)
	public ModelAndView deleteTask(@PathVariable Integer taskId, RedirectAttributes redirectAttributes) {
		Task task = this.taskService.findTaskById(taskId);
		this.taskService.deleteTask(task);

		this.postSingleMessage(redirectAttributes, new Message(MessageType.SUCCESS, "success.task.delete", task.getName()));

		return new ModelAndView("redirect:/tasks/" + task.getProject().getProjectId());
	}

	@RequestMapping(value = "/plan/{projectId}", method = RequestMethod.GET)
	public ModelAndView showPlan(@PathVariable Integer projectId) {
		Project project = this.projectService.findProjectById(projectId);
		// Transform to ProjectBean
		Map<String, BigDecimal> projectDurations = this.projectService.calculateProjectDuration(project);
		ProjectBean projectBean = new ProjectBean(project, projectDurations);

		List<Task> tasks = this.taskService.listTasksByProject(projectId);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectBean);
		mav.addObject("plan", new PlanningBean(tasks));
		mav.addObject("uiUtils", UIUtils.getInstance());
		mav.setViewName("projectPlan");

		return mav;
	}

	private List<TaskBean> prepareBeans(List<Task> tasks) {
		List<TaskBean> taskBeans = new ArrayList<TaskBean>();
		for (Task task : tasks) {
			taskBeans.add(new TaskBean(task));
		}

		return taskBeans;
	}

	private List<Task> sortTasksByDeadline(Project project) {
		List<Task> tasks = new ArrayList<Task>();
		tasks.addAll(project.getTasks());
		Collections.sort(tasks, new TaskComparator());
		return tasks;
	}

	private ModelAndView prepareTaskListView(ModelAndView mav, Integer projectId) {
		Project project = this.projectService.findProjectById(projectId);
		// Sort list of tasks
		List<Task> tasks = this.sortTasksByDeadline(project);

		mav.addObject("project", project);
		mav.addObject("taskTypes", TaskType.values());
		mav.addObject("tasks", this.prepareBeans(tasks));
		mav.addObject("hoursByType", this.projectService.calculateProjectDurationByTaskType(project));
		mav.setViewName("projectTaskMatrix");
		return mav;
	}
}
