
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface RankDao {

	public List<Map<String, Object>> findVipRankByProjectId(Map<String, Object> map);
	
	public List<Map<String, Object>> findVipInfo(Map<String, Object> map);

}
