
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface MsgDao {

	public List<Map<String, Object>> findMsg(Map<String, Object> map);

	public List<Map<String, Object>> findMsgByUser(String userId);

	public int readMsg(String msgId);
	
	public int delete(String msgId);
}
