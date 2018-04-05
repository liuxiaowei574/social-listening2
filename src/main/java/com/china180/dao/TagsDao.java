
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface TagsDao {

	public List<Map<String, Object>> findTagsByProjectId(Map<String, Object> map);

}
