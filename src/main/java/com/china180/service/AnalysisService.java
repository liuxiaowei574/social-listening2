package com.china180.service;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

	public List<Map<String, Object>> findSoundByRange(Map<String, Object> map);

	public List<Map<String, Object>> findTagsByProjectId(Map<String, Object> map);
	
	public List<Map<String, Object>> findVipRankByProjectId(Map<String, Object> map);
	
	public List<Map<String, Object>> findVipInfo(Map<String, Object> map);
	
	public List<Map<String, Object>> findTopicWordsByProjectId(Map<String, Object> map);
}
