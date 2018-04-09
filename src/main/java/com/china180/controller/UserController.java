package com.china180.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.china180.service.UserService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/UserManager")
	public Map<String, Object> userManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();

		String module = StringUtils.defaultString(request.getParameter("module"), "");
		String login_name = StringUtils.defaultString(request.getParameter("login_name"), "");
		String password = StringUtils.defaultString(request.getParameter("password"), "");
		String user_level = StringUtils.defaultString(request.getParameter("user_level"), "1");
		String user_status = StringUtils.defaultString(request.getParameter("user_status"), "1");
		logger.info("*****接收参数****** module={} login_name={} password={} user_level={} user_status={}", module,
				login_name, password, user_level, user_status);

		if (StringUtils.isAnyBlank(module)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}
		if ("insert".equals(module)) {
			Map<String, Object> user = userService.findByLoginName(login_name);
			if (user != null && user.size() > 0) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1001", "", data);
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("login_name", login_name);
			paramMap.put("password", password);
			paramMap.put("user_level", Integer.parseInt(user_level));
			paramMap.put("user_status", Integer.parseInt(user_status));
			int result = userService.insert(paramMap);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("userlist".equals(module)) {
			String sSearch = StringUtils.defaultString(request.getParameter("sSearch"), "");
			String iDisplayStart = StringUtils.defaultString(request.getParameter("iDisplayStart"),
					Constant.DEFAULT_PAGE_NUM);
			String iDisplayLength = StringUtils.defaultString(request.getParameter("iDisplayLength"),
					Constant.DEFAULT_PAGE_SIZE);
			long iTotalRecords = 0L;
			if (StringUtils.isNotBlank(sSearch)) {
				sSearch = new String(sSearch.getBytes("iso-8859-1"), "utf-8");
			}

			// 只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
			PageHelper.startPage(Integer.parseInt(iDisplayStart), Integer.parseInt(iDisplayLength));
			List<Map<String, Object>> list = userService.findListByLoginName(sSearch);
			// 需要把Page包装成PageInfo对象才能序列化
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
			iTotalRecords = pageInfo.getTotal();

			JSONArray aaData = new JSONArray();
			for (Map<String, Object> map : list) {
				JSONArray record = new JSONArray();
				record.add(map.get("user_id"));
				record.add(map.get("login_name"));
				record.add(map.get("user_status"));
				record.add(map.get("user_level"));
				record.add(map.get("user_pic"));
				
				aaData.add(record);
			}
			returnMap.put("sEcho", request.getParameter("sEcho"));
			returnMap.put("iTotalRecords", iTotalRecords);
			returnMap.put("iTotalDisplayRecords", iTotalRecords);
			returnMap.put("aaData", aaData);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("select".equals(module)) {
			String user_id = StringUtils.defaultString(request.getParameter("user_id"), "");
			Map<String, Object> user = userService.findByUserId(user_id);
			data.put("loginname", user.get("login_name"));
			data.put("user_status", user.get("user_status"));
			data.put("user_level", user.get("user_level"));
			data.put("user_pic", user.get("user_pic"));
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("update".equals(module)) {
			String user_id = StringUtils.defaultString(request.getParameter("user_id"), "");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("user_level", Integer.parseInt(user_level));
			paramMap.put("user_status", Integer.parseInt(user_status));
			paramMap.put("user_id", user_id);
			int result = userService.update(paramMap);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("change_psw".equals(module)) {
			String user_id = StringUtils.defaultString(request.getParameter("user_id"), "");
			String newPwd = StringUtils.defaultString(request.getParameter("password"), "");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("password", newPwd);
			paramMap.put("user_id", user_id);
			int result = userService.update(paramMap);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("listall".equals(module)) {
			List<Map<String, Object>> list = userService.findListByLoginName("");
			JSONArray aaData = new JSONArray();
			for (Map<String, Object> map : list) {
				JSONArray record = new JSONArray();
				record.add(map.get("user_id"));
				record.add(map.get("login_name"));
				
				aaData.add(record);
			}
			returnMap.put("aaData", aaData);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
