package com.china180.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.china180.service.SoundService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;

@RestController
public class AnalysisController extends AbstractController {
	
	private static final String START_TIME = " 00:00:00";

	@Resource
	private SoundService soundService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Analysis")
	public Map<String, Object> insert(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "module", required = false) String module) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		logger.info("*****接收参数****** module={} ", module);

		if (StringUtils.isAnyBlank(module)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "2", "", data);
		}

		if ("sound".equals(module)) {
			String project_id = request.getParameter("project_id");
			String start_time = request.getParameter("start_time").substring(0, 10);
			String end_time = request.getParameter("end_time").substring(0, 10);
			String source_id = request.getParameter("source_id");
			if(StringUtils.isNotBlank(source_id)) {
				source_id = source_id.replaceAll(" ", "+");
			}
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_id", project_id);
			paramMap.put("start_time", start_time + START_TIME);
			paramMap.put("end_time", end_time + START_TIME);
			paramMap.put("sourceList", Arrays.asList(source_id.split("\\+")));
			List<Map<String, Object>> list = soundService.findByRange(paramMap);
			
			returnMap.put(Constant.RESULTCODE, Constant.SUCCESS);
			returnMap.put(Constant.STATUS, Constant.OK);
			returnMap.put(Constant.DATA, list);
			return returnMap;
		} else if ("userlist".equals(module)) {

		} else if ("update".equals(module)) {

		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
