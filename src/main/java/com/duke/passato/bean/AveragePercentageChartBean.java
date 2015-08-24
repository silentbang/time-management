package com.duke.passato.bean;

public class AveragePercentageChartBean {

	private String projectName;
	private Double averagePercentage;

	public AveragePercentageChartBean(String projectName, Double averagePercentage) {
		this.projectName = projectName;
		this.averagePercentage = averagePercentage;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getAveragePercentage() {
		return averagePercentage;
	}

	public void setAveragePercentage(Double averagePercentage) {
		this.averagePercentage = averagePercentage;
	}

}
