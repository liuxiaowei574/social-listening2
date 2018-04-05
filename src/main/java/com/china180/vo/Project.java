package com.china180.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Project implements Serializable {
	private static final long serialVersionUID = -6960661153669286734L;

	private int projectId;
	private String projectName;
	private String projectUserBelong;
	private int projectStatus;
	private int projectPriority;
	private int projectType;
	private String projectBrand;
	private String projectInfo;
	private String projectKeyWords;
	private String projectSource;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp projectCreateTime;
	private String projectProduct;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectUserBelong() {
		return projectUserBelong;
	}

	public void setProjectUserBelong(String projectUserBelong) {
		this.projectUserBelong = projectUserBelong;
	}

	public int getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}

	public int getProjectPriority() {
		return projectPriority;
	}

	public void setProjectPriority(int projectPriority) {
		this.projectPriority = projectPriority;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public String getProjectBrand() {
		return projectBrand;
	}

	public void setProjectBrand(String projectBrand) {
		this.projectBrand = projectBrand;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getProjectKeyWords() {
		return projectKeyWords;
	}

	public void setProjectKeyWords(String projectKeyWords) {
		this.projectKeyWords = projectKeyWords;
	}

	public String getProjectSource() {
		return projectSource;
	}

	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getProjectProduct() {
		return projectProduct;
	}

	public void setProjectProduct(String projectProduct) {
		this.projectProduct = projectProduct;
	}

	public Timestamp getProjectCreateTime() {
		return projectCreateTime;
	}

	public void setProjectCreateTime(Timestamp projectCreateTime) {
		this.projectCreateTime = projectCreateTime;
	}

}
