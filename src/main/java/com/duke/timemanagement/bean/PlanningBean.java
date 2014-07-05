package com.duke.timemanagement.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.duke.timemanagement.common.DateUtils;
import com.duke.timemanagement.model.Task;

public class PlanningBean {
	private Map<String, DayBean> tasksByDays;
	private final Set<String> dateTexts;

	private final DateUtils dateUtils;

	public PlanningBean(List<Task> tasks) {
		// TODO Autowire instead
		this.dateUtils = new DateUtils();// Init instead of autowire
		this.dateTexts = new HashSet<String>();
		this.populateTasksByDays(tasks);
	}

	private void populateTasksByDays(List<Task> tasks) {
		// Populate lists of unique dates
		for (Task task : tasks) {
			String dateText = this.dateUtils.convertToDateText(task.getDeadline());
			this.dateTexts.add(dateText);
		}

		// Populate maps
		Map<String, DayBean> unsortedTasksByDays = new HashMap<String, DayBean>();
		for (String dateText : this.dateTexts) {
			// Add to list of tasks for a specific day
			DayBean dayBean = new DayBean();
			List<Task> tasksByDay = new ArrayList<Task>();
			for (Task task : tasks) {
				String taskDeadline = this.dateUtils.convertToDateText(task.getDeadline());
				if (dateText.equals(taskDeadline)) {
					tasksByDay.add(task);
				}
			}

			// Compute
			dayBean.setTasks(tasksByDay);
			double totalProgress = 0;
			double totalEstimatedDuration = 0;
			double totalActualDuration = 0;
			for (int i = 0; i < tasksByDay.size(); i++) {
				Task task = tasksByDay.get(i);
				totalProgress += (task.getCompletedPercentage() == null) ? 0 : task.getCompletedPercentage();
				totalEstimatedDuration += task.getEstimatedDuration();
				totalActualDuration += (task.getActualDuration() == null) ? 0 : task.getActualDuration();
			}
			Double averageProgress = totalProgress / tasksByDay.size();

			dayBean.setAverageProgress(averageProgress);
			dayBean.setTotalEstimatedDuration(totalEstimatedDuration);
			dayBean.setTotalActualDuration(totalActualDuration);
			// Add to map
			unsortedTasksByDays.put(dateText, dayBean);
		}

		// Sort map by date (descending)
		this.tasksByDays = new TreeMap<String, DayBean>(Collections.reverseOrder());
		this.tasksByDays.putAll(unsortedTasksByDays);
	}

	public Map<String, DayBean> getTasksByDays() {
		return this.tasksByDays;
	}

	public void setTasksByDays(Map<String, DayBean> tasksByDays) {
		this.tasksByDays = tasksByDays;
	}

}
