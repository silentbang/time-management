package com.duke.timemanagement.bean;

import java.util.Date;
import java.util.List;

import com.duke.timemanagement.model.Task;

public class DayBean {

	private Date date;
	private Date totalEstimatedDuration;
	private Date totalActualDuration;
	private Double averageProgress;
	private List<Task> tasks;

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTotalEstimatedDuration() {
		return this.totalEstimatedDuration;
	}

	public void setTotalEstimatedDuration(Date totalEstimatedDuration) {
		this.totalEstimatedDuration = totalEstimatedDuration;
	}

	public Date getTotalActualDuration() {
		return this.totalActualDuration;
	}

	public void setTotalActualDuration(Date totalActualDuration) {
		this.totalActualDuration = totalActualDuration;
	}

	public Double getAverageProgress() {
		return this.averageProgress;
	}

	public void setAverageProgress(Double averageProgress) {
		this.averageProgress = averageProgress;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
