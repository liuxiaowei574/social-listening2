package com.china180.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.UserDao;
import com.china180.vo.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public List<Map<String, Object>> findAll() {
		return userDao.findAll();
	}

	public List<Map<String, Object>> findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public List<Map<String, Object>> findByProperties(Map<String, Object> map) {
		return userDao.findByProperties(map);
	}
	
	public List<User> login(String loginName) {
		return userDao.login(loginName);
	}

	public int insert(Map<String, Object> map) {
		return userDao.insert(map);
	}

}
