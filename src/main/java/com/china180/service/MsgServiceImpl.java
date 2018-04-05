package com.china180.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.MsgDao;

@Service
@Transactional
public class MsgServiceImpl implements MsgService {
	@Resource
	private MsgDao msgDao;

	@Override
	public List<Map<String, Object>> findMsg(Map<String, Object> map) {
		return msgDao.findMsg(map);
	}

	@Override
	public List<Map<String, Object>> findMsgByUser(String userId) {
		return msgDao.findMsgByUser(userId);
	}

	@Override
	public int readMsg(String msgId) {
		return msgDao.readMsg(msgId);
	}

}
