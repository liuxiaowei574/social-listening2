package com.china180.service;

import java.util.List;
import java.util.Map;

import com.china180.vo.Project;

public interface ProjectService {

	public List<Map<String, Object>> findById(String project_id);

	public List<Map<String, Object>> findByUser(String userId, String projectType);

	public List<Map<String, Object>> findByUserAndProject(String userId, String projectType, String projectName);

	public List<Map<String, Object>> findByProperties(Map<String, Object> map);

	public int insert(Project project);

	public int update(Map<String, Object> map);
}
