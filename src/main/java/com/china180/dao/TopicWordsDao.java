
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface TopicWordsDao {

	public List<Map<String, Object>> findTopicWordsByProjectId(Map<String, Object> map);

}
