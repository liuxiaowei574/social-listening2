
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface ProjectDao {

	public List<Map<String, Object>> findById(String project_id);
	
	public int insert(Map<String, Object> map);
	
	public int update(Map<String, Object> map);
}
