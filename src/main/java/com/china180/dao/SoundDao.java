
package com.china180.dao;

import java.util.List;
import java.util.Map;

public interface SoundDao {

	public List<Map<String, Object>> findByRange(Map<String, Object> map);

}
