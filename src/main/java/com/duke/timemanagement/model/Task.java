package com.duke.timemanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.duke.timemanagement.common.Constant;

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
	private Double estimatedDuration;

	@Column(name = "\"actualDuration\"")
	private Double actualDuration;

	@Column(name = "\"deadline\"")
	@DateTimeFormat(pattern = Constant.FORMAT_DATE_TIME)
	private Date deadline;

	@Column(name = "\"note\"")
	private String note;

	@Column(name = "\"completedPercentage\"")
	private Double completedPercentage;

	@Column(name = "\"isFinished\"")
	private Boolean isFinished;

	@Column(name = "\"taskTypeId\"")
	private Integer taskTypeId;

	@ManyToOne
	@JoinColumn(name = "\"projectId\"")
	private Project project;

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

	public Double getEstimatedDuration() {
		return this.estimatedDuration;
	}

	public void setEstimatedDuration(Double estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public Double getActualDuration() {
		return this.actualDuration;
	}

	public void setActualDuration(Double actualDuration) {
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

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getTaskTypeId() {
		return this.taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

}
