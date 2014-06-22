package com.duke.timemanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.duke.timemanagement.bean.TaskBean;
import com.duke.timemanagement.common.Constant;
import com.duke.timemanagement.common.TaskType;
import com.duke.timemanagement.model.Project;
import com.duke.timemanagement.model.Task;
import com.duke.timemanagement.service.ProjectService;
import com.duke.timemanagement.service.TaskService;

@Controller
@RequestMapping(value = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
	public ModelAndView updateTaskMatrix(@PathVariable Integer projectId) {
		Project project = this.projectService.findProjectById(projectId);
		TaskBean taskBean = new TaskBean();
		taskBean.setProjectId(project.getProjectId());
		// FIXME
		taskBean.setTaskTypeId(1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project", project);
		mav.addObject("taskTypes", TaskType.values());
		mav.addObject("tasks", project.getTasks());
		mav.addObject("task", taskBean);
		mav.setViewName("projectTaskMatrix");

		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTask(@ModelAttribute("task") TaskBean taskBean, BindingResult result) {
		Task task = this.prepareModel(taskBean);
		this.taskService.saveTask(task);

		return new ModelAndView("redirect:/tasks/" + task.getProject().getProjectId());
	}

	// TODO Use JSON
	@RequestMapping(value = "/delete/{taskId}", method = RequestMethod.GET)
	public ModelAndView deleteTask(@PathVariable Integer taskId) {
		Task task = this.taskService.findTaskById(taskId);
		this.taskService.deleteTask(task);

		return new ModelAndView("redirect:/tasks/" + task.getProject().getProjectId());
	}

	private Task prepareModel(TaskBean taskBean) {
		// Transform deadline
		Date deadline = this.transformDeadline(taskBean);

		Task task = new Task();
		task.setTaskId(taskBean.getTaskId());
		task.setProject(this.projectService.findProjectById(taskBean.getProjectId()));
		task.setTaskTypeId(taskBean.getTaskTypeId());
		task.setName(taskBean.getName());
		task.setEstimatedDuration(taskBean.getEstimatedDuration());
		task.setActualDuration(taskBean.getActualDuration());
		task.setDeadline(deadline);
		task.setNote(taskBean.getNote());
		task.setCompletedPercentage(taskBean.getCompletedPercentage());
		task.setIsFinished(taskBean.getIsFinished());

		return task;
	}

	@SuppressWarnings("deprecation")
	private Date transformDeadline(TaskBean taskBean) {
		Date deadlineDate = taskBean.getDeadlineDate();
		Date deadlineTime = taskBean.getDeadlineTime();

		Date deadline = new Date();
		deadline.setYear(deadlineDate.getYear());
		deadline.setMonth(deadlineDate.getMonth());
		deadline.setDate(deadlineDate.getDate());
		deadline.setHours(deadlineTime.getHours());
		deadline.setMinutes(deadlineTime.getMinutes());
		deadline.setSeconds(deadlineTime.getSeconds());
		return deadline;
	}
}
