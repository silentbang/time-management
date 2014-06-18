package com.duke.timemanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "project")
public class Project implements Serializable {

	private static final long serialVersionUID = 529761514104085030L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"projectId\"")
	private Integer projectId;

	@Column(name = "\"name\"")
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"createdDate\"", nullable = false, updatable = false)
	private Date createdDate;

	public Project() {
		this.createdDate = new Date();
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

}
