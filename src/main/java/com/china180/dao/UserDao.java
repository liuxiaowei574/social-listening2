
package com.china180.dao;

import java.util.List;
import java.util.Map;

import com.china180.vo.User;

public interface UserDao {

	public List<Map<String, Object>> findAll();
	
	public List<Map<String, Object>> findByLoginName(String loginName);
	
	public List<User> login(String loginName);

	public List<Map<String, Object>> findByProperties(Map<String, Object> map);

	public int insert(Map<String, Object> map);
}
