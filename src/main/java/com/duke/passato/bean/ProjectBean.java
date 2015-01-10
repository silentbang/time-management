package com.duke.passato.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;

public class ProjectBean implements TransferableBean<ProjectBean, Project> {
	private Integer projectId;
	@NotBlank
	private String name;
	private Date createdDate;
	private Double totalEstimatedDuration;
	private Double totalActualDuration;
	private Double averageProgress;

	public ProjectBean() {
	}

	public ProjectBean(Project project, Map<String, BigDecimal> projectDurations) {
		this.setProjectId(project.getProjectId());
		this.setName(project.getName());
		this.setCreatedDate(project.getCreatedDate());
		this.setTotalEstimatedDuration(projectDurations.get(Constant.Tag.SUM_TOTALESTIMATEDDURATION).doubleValue());
		this.setTotalActualDuration(projectDurations.get(Constant.Tag.SUM_TOTALACTUALDURATION).doubleValue());
		this.setAverageProgress(projectDurations.get(Constant.Tag.SUM_AVERAGEPROGRESS).doubleValue());
	}

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

	public Double getAverageProgress() {
		return this.averageProgress;
	}

	public void setAverageProgress(Double averageProgress) {
		this.averageProgress = averageProgress;
	}

	@Override
	public Project transformIntoModel() {
		Project project = new Project();
		BeanUtils.copyProperties(this, project);

		return project;
	}

}
