package com.duke.passato.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "\"project\"")
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = {
		CascadeType.REMOVE
	})
	// Prevent duplicates when joining
	@Fetch(FetchMode.SUBSELECT)
	private Set<Task> tasks;

	public Project() {
		// Initialize current timestamp
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

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
