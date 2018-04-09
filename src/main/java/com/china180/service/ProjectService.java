package com.china180.service;

import java.util.List;
import java.util.Map;

import com.china180.vo.Project;

public interface ProjectService {

	public Map<String, Object> findById(String project_id);
	
	public List<Map<String, Object>> findListByProjectName(String project_name);
	
	public List<Map<String, Object>> findListBackbone(String project_id);

	public List<Map<String, Object>> findByUser(String userId, String projectType);

	public List<Map<String, Object>> findByUserAndProject(String userId, String projectType, String projectName);

	public List<Map<String, Object>> findByProperties(Map<String, Object> map);

	public List<Map<String, Object>> findSpiderTask();
	
	public List<Map<String, Object>> findEngineTask();

	public int insert(Project project);

	public int update(Map<String, Object> map);
}
