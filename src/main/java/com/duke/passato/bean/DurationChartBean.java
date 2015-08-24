package com.duke.passato.bean;

public class DurationChartBean {

	private String projectName;
	private Double estimatedDuration;
	private Double actualDuration;

	public DurationChartBean(String projectName, Double estimatedDuration, Double actualDuration) {
		this.projectName = projectName;
		this.estimatedDuration = estimatedDuration;
		this.actualDuration = actualDuration;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(Double estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public Double getActualDuration() {
		return actualDuration;
	}

	public void setActualDuration(Double actualDuration) {
		this.actualDuration = actualDuration;
	}

}
