package com.duke.timemanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.duke.timemanagement.bean.TaskBean;
import com.duke.timemanagement.common.Constant;
import com.duke.timemanagement.common.TaskType;
import com.duke.timemanagement.common.UIUtils;
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

		ModelAndView mav = new ModelAndView();
		mav.addObject("project", project);
		mav.addObject("taskTypes", TaskType.values());
		mav.addObject("tasks", project.getTasks());
		mav.addObject("task", taskBean);
		mav.addObject("hoursByType", this.projectService.calculateProjectDurationByTaskType(project));
		mav.setViewName("projectTaskMatrix");

		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTask(@ModelAttribute("task") TaskBean taskBean, BindingResult result) {
		Task task = this.prepareModel(taskBean);
		this.taskService.saveTask(task);

		return new ModelAndView("redirect:/tasks/" + task.getProject().getProjectId());
	}

	@RequestMapping(value = "/update/{taskId}", method = RequestMethod.POST)
	public @ResponseBody
	TaskBean updateTask(@PathVariable Integer taskId) {
		Task task = this.taskService.findTaskById(taskId);
		TaskBean taskBean = this.prepareBean(task);

		return taskBean;
	}

	// TODO Use JSON
	@RequestMapping(value = "/delete/{taskId}", method = RequestMethod.GET)
	public ModelAndView deleteTask(@PathVariable Integer taskId) {
		Task task = this.taskService.findTaskById(taskId);
		this.taskService.deleteTask(task);

		return new ModelAndView("redirect:/tasks/" + task.getProject().getProjectId());
	}

	@RequestMapping(value = "/plan/{projectId}", method = RequestMethod.GET)
	public ModelAndView showPlan(@PathVariable Integer projectId) {
		Project project = this.projectService.findProjectById(projectId);

		List<Task> tasks = this.taskService.listTasksByProject(projectId);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project", project);
		mav.addObject("tasks", tasks);
		mav.addObject("uiUtils", UIUtils.getInstance());
		mav.setViewName("projectPlan");

		return mav;
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

	private TaskBean prepareBean(Task task) {
		TaskBean taskBean = new TaskBean();
		taskBean.setTaskId(task.getTaskId());
		taskBean.setProjectId(task.getProject().getProjectId());
		taskBean.setTaskTypeId(task.getTaskTypeId());
		taskBean.setName(task.getName());
		taskBean.setEstimatedDuration(task.getEstimatedDuration());
		taskBean.setActualDuration(task.getActualDuration());
		taskBean.setDeadlineDate(task.getDeadline());
		taskBean.setDeadlineTime(task.getDeadline());
		taskBean.setDeadlineDateText(new SimpleDateFormat(Constant.FORMAT_DATE).format(task.getDeadline()));
		taskBean.setDeadlineTimeText(new SimpleDateFormat(Constant.FORMAT_TIME).format(task.getDeadline()));

		taskBean.setNote(task.getNote());
		taskBean.setCompletedPercentage(task.getCompletedPercentage());
		taskBean.setIsFinished(taskBean.getIsFinished());

		return taskBean;
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
