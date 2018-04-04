package com.china180.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.SoundDao;

@Service
@Transactional
public class SoundServiceImpl implements SoundService {

	@Resource
	private SoundDao soundDao;

	@Override
	public List<Map<String, Object>> findByRange(Map<String, Object> map) {
		return soundDao.findByRange(map);
	}

}
