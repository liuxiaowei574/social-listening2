package com.china180.controller;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.china180.service.AnalysisService;
import com.china180.util.ResponseMapUtil;

@RestController
public class AnalysisController extends BaseController {

	private static final String START_TIME = " 00:00:00";

	@Resource
	private AnalysisService analysisService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		String module = StringUtils.defaultString(request.getParameter("module"), "");
		String project_id = StringUtils.defaultString(request.getParameter("project_id"), "");
		logger.info("*****接收参数****** module={} project_id={}", module, project_id);

		if (StringUtils.isAnyBlank(module, project_id)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}

		if ("sound".equals(module)) {
			String start_time = StringUtils.defaultString(request.getParameter("start_time"), "");
			String end_time = StringUtils.defaultString(request.getParameter("end_time"), "");
			if (start_time.length() < 10 || end_time.length() < 10) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
			}
			start_time = start_time.substring(0, 10);
			end_time = end_time.substring(0, 10);
			String source_id = StringUtils.defaultString(request.getParameter("source_id"), "");
			if (StringUtils.isNotBlank(source_id)) {
				source_id = source_id.replaceAll(" ", "+");
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_id", project_id);
			paramMap.put("start_time", start_time + START_TIME);
			paramMap.put("end_time", end_time + START_TIME);
			paramMap.put("sourceList", Arrays.asList(source_id.split("\\+")));
			List<Map<String, Object>> list = analysisService.findSoundByRange(paramMap);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", list);
		} else if ("tags".equals(module)) {
			JSONArray jsonArray = new JSONArray();
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_id", project_id);
			List<Map<String, Object>> list = analysisService.findTagsByProjectId(paramMap);
			if (list == null || list.size() < 1) {
				return ResponseMapUtil.setDefultSuccess(returnMap, "", jsonArray);
			}
			String tags = String.valueOf(list.get(0).get("tags"));
			String[] tagArray = tags.replace(" ", "").replace("{", "").replace("}", "").replace("\"", "").split(",");
			if (tagArray == null || tagArray.length < 1) {
				return ResponseMapUtil.setDefultSuccess(returnMap, "", jsonArray);
			}
			for (int i = 0, len = tagArray.length; i < len; i++) {
				JSONObject jsonObject = new JSONObject(true);
				jsonObject.put("name", tagArray[i].split(":")[0]);
				jsonObject.put("value", Integer.parseInt(tagArray[i].split(":")[1]));
				jsonArray.add(jsonObject);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", jsonArray);
		} else if ("rank".equals(module)) {
			String source_id = StringUtils.defaultString(request.getParameter("source_id"), "");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_id", project_id);
			paramMap.put("source_id", source_id);
			List<Map<String, Object>> list = analysisService.findVipRankByProjectId(paramMap);
			if (list == null || list.size() < 1) {
				return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
			}
			DecimalFormat format = new DecimalFormat("#.00");
			for (int i = 0, len = list.size(); i < len; i++) {
				Double score = Double.valueOf(String.valueOf(list.get(i).get("rank_score")));
				list.get(i).put("rank_score", format.format(score * 10000));
			}
			if ("1".equals(source_id)) {
				for (int i = 0, len = list.size(); i < len; i++) {
					String vipId = (String) list.get(i).get("vip_id");
					int limit = (int) (Math.random() * 10) % 5;
					paramMap = new HashMap<>();
					paramMap.put("vip_id", vipId);
					paramMap.put("vip_id_1", vipId);
					paramMap.put("limit", limit);
					List<Map<String, Object>> childrenList = analysisService.findVipInfo(paramMap);
					list.get(i).put("child", childrenList);
				}
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("topic_words".equals(module)) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_id", project_id);
			List<Map<String, Object>> list = analysisService.findTopicWordsByProjectId(paramMap);
			if (list == null || list.size() < 1) {
				return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
			}
			JSONArray jsonArray = new JSONArray();
			String tags = String.valueOf(list.get(0).get("topic_words"));
			String[] tagArray = tags.replace(" ", "").replace("{", "").replace("}", "").replace("\"", "").split(",");
			if (tagArray == null || tagArray.length < 1) {
				return ResponseMapUtil.setDefultSuccess(returnMap, "", jsonArray);
			}
			JSONObject jsonObject = new JSONObject(true);
			for (int i = 0, len = tagArray.length; i < len; i++) {
				jsonObject.put(tagArray[i].split(":")[0], Integer.parseInt(tagArray[i].split(":")[1]));
			}

			int maxWeight = (Integer) Collections.max(jsonObject.values(), new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					return Integer.valueOf(String.valueOf(o1)).compareTo(Integer.valueOf(String.valueOf(o2)));
				}
			});
			Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				int originalValue = (int) entry.getValue();
				int fixedValue = (int) (((double) originalValue / maxWeight) * 25);
				JSONObject object = new JSONObject();
				object.put("name", entry.getKey());
				object.put("value", fixedValue);
				jsonArray.add(object);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", jsonArray);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
