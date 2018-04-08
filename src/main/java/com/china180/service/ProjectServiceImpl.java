package com.china180.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.ProjectDao;
import com.china180.vo.Project;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectDao projectDao;

	public List<Map<String, Object>> findById(String project_id) {
		return projectDao.findById(project_id);
	}

	public List<Map<String, Object>> findByProperties(Map<String, Object> map) {
		return projectDao.findByProperties(map);
	}

	public int insert(Project project) {
		return projectDao.insert(project);
	}

	public int update(Map<String, Object> map) {
		return projectDao.update(map);
	}

	@Override
	public List<Map<String, Object>> findByUser(String userId, String projectType) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("project_user_belong", userId);
		paramMap.put("project_type", projectType);
		return projectDao.findByProperties(paramMap);
	}

	@Override
	public List<Map<String, Object>> findByUserAndProject(String userId, String projectType, String projectName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("project_user_belong", userId);
		paramMap.put("project_type", projectType);
		paramMap.put("project_name", projectName);
		return projectDao.findByProperties(paramMap);
	}

	@Override
	public List<Map<String, Object>> findSpiderTask() {
		return projectDao.findSpiderTask();
	}
	
	@Override
	public List<Map<String, Object>> findEngineTask() {
		return projectDao.findEngineTask();
	}

}
