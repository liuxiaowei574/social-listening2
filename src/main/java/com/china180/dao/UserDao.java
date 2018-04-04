
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {

	public List<Map<String, Object>> findAll();
	
	public List<Map<String, Object>> findByLoginName(String loginName);
	
	public List<Map<String, Object>> login(String loginName);

	public List<Map<String, Object>> findByProperties(Map<String, Object> map);

	public int insert(Map<String, Object> map);
}
