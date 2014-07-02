package com.duke.timemanagement.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.duke.timemanagement.common.Helper;
import com.duke.timemanagement.model.Task;

public class PlanningBean {
	private Map<String, DayBean> tasksByDays;
	private final Set<String> dateTexts;

	public PlanningBean(List<Task> tasks) {
		this.dateTexts = new HashSet<String>();
		this.populateTasksByDays(tasks);
	}

	private void populateTasksByDays(List<Task> tasks) {
		// Populate lists of unique dates
		for (Task task : tasks) {
			String dateText = Helper.convertToDateText(task.getDeadline());
			this.dateTexts.add(dateText);
		}

		// Populate maps
		Map<String, DayBean> unsortedTasksByDays = new HashMap<String, DayBean>();
		for (String dateText : this.dateTexts) {
			// Add to list of tasks for a specific day
			DayBean dayBean = new DayBean();
			List<Task> tasksByDay = new ArrayList<Task>();
			for (Task task : tasks) {
				String taskDeadline = Helper.convertToDateText(task.getDeadline());
				if (dateText.equals(taskDeadline)) {
					tasksByDay.add(task);
				}
			}
			// Add to map
			dayBean.setTasks(tasksByDay);
			unsortedTasksByDays.put(dateText, dayBean);
		}

		// Sort map by date
		this.tasksByDays = new TreeMap<String, DayBean>(unsortedTasksByDays);
	}

	public Map<String, DayBean> getTasksByDays() {
		return this.tasksByDays;
	}

	public void setTasksByDays(Map<String, DayBean> tasksByDays) {
		this.tasksByDays = tasksByDays;
	}

}
