package com.china180.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	public List<Map<String, Object>> findAll();

	public List<Map<String, Object>> findByLoginName(String loginName);
	
	public List<Map<String, Object>> findByProperties(Map<String, Object> map);
	
	public List<Map<String, Object>> login(String loginName);

	public int insert(Map<String, Object> map);
}
