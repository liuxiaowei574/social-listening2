package com.china180.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public List<Map<String, Object>> findListByLoginName(String loginName) {
		return userDao.findListByLoginName(loginName);
	}

	@Override
	public Map<String, Object> findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	@Override
	public Map<String, Object> findByUserId(String userId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", userId);
		List<Map<String, Object>> list = userDao.findByProperties(paramMap);
		if (list == null) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Map<String, Object>> findByProperties(Map<String, Object> map) {
		return userDao.findByProperties(map);
	}

	@Override
	public List<Map<String, Object>> login(String loginName) {
		return userDao.login(loginName);
	}

	@Override
	public int insert(Map<String, Object> map) {
		return userDao.insert(map);
	}
	
	@Override
	public int update(Map<String, Object> map) {
		return userDao.update(map);
	}

}
