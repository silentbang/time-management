package com.duke.passato.bean;

import java.util.Date;

public class ProjectBean {
	private Integer projectId;
	private String name;
	private Date createdDate;
	private Double totalEstimatedDuration;
	private Double totalActualDuration;

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

}
