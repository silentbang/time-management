package com.duke.timemanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task implements Serializable {

	private static final long serialVersionUID = -4400842752968299857L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"taskId\"")
	private Integer taskId;

	@Column(name = "\"name\"")
	private String name;

	@Column(name = "\"estimatedDuration\"")
	private Integer estimatedDuration;

	@Column(name = "\"actualDuration\"")
	private Integer actualDuration;

	@Column(name = "\"deadline\"")
	private Date deadline;

	@Column(name = "\"note\"")
	private String note;

	@Column(name = "\"completedPercentage\"")
	private Double completedPercentage;

	@Column(name = "\"isFinished\"")
	private Boolean isFinished;

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEstimatedDuration() {
		return this.estimatedDuration;
	}

	public void setEstimatedDuration(Integer estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public Integer getActualDuration() {
		return this.actualDuration;
	}

	public void setActualDuration(Integer actualDuration) {
		this.actualDuration = actualDuration;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getCompletedPercentage() {
		return this.completedPercentage;
	}

	public void setCompletedPercentage(Double completedPercentage) {
		this.completedPercentage = completedPercentage;
	}

	public Boolean getIsFinished() {
		return this.isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

}
