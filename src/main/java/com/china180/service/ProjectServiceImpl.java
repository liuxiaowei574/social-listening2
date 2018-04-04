package com.china180.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.ProjectDao;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectDao projectDao;

	public List<Map<String, Object>> findById(String project_id) {
		return projectDao.findById(project_id);
	}

	public int insert(Map<String, Object> map) {
		return projectDao.insert(map);
	}

	public int update(Map<String, Object> map) {
		return projectDao.update(map);
	}

}
