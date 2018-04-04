package com.china180.service;

import java.util.List;
import java.util.Map;

import com.china180.vo.User;

public interface UserService {
	
	public List<Map<String, Object>> findAll();

	public List<Map<String, Object>> findByLoginName(String loginName);
	
	public List<Map<String, Object>> findByProperties(Map<String, Object> map);
	
	public List<User> login(String loginName);

	public int insert(Map<String, Object> map);
}
