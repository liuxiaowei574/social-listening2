package com.china180.service;

import java.util.List;
import java.util.Map;

public interface MsgService {

	public List<Map<String, Object>> findMsg(Map<String, Object> map);

	public List<Map<String, Object>> findMsgByUser(String userId);

	public int readMsg(String msgId);
}
