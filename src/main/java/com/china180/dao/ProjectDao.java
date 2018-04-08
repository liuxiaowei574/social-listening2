
package com.china180.dao;

import java.util.List;
import java.util.Map;

import com.china180.vo.Project;

public interface ProjectDao {

	public List<Map<String, Object>> findById(String project_id);

	public List<Map<String, Object>> findByProperties(Map<String, Object> map);

	public List<Map<String, Object>> findSpiderTask();
	
	public List<Map<String, Object>> findEngineTask();

	public int insert(Project project);

	public int update(Map<String, Object> map);
}
