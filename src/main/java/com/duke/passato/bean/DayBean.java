package com.duke.passato.bean;

import java.util.Date;
import java.util.List;

import com.duke.passato.model.Task;

public class DayBean {

	private Date date;
	private Double totalEstimatedDuration;
	private Double totalActualDuration;
	private Double averageProgress;
	private List<Task> tasks;

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalEstimatedDuration() {
		return this.totalEstimatedDuration;
	}

	public void setTotalEstimatedDuration(Double totalEstimatedDuration) {
		this.totalEstimatedDuration = totalEstimatedDuration;
	}

	public Double getTotalActualDuration() {
		return this.totalActualDuration;
	}

	public void setTotalActualDuration(Double totalActualDuration) {
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
