package com.china180.controller;

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

import com.china180.service.UserService;
import com.china180.util.ResponseMapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/UserManager/listAll")
	public Map<String, Object> listAll(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();

		// 只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> userList = userService.findAll();
		// 需要把Page包装成PageInfo对象才能序列化
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(userList);
		returnMap.put("pageInfo", pageInfo);
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/UserManager")
	public Map<String, Object> userManager(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "login_name", required = false) String login_name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "user_level", required = false, defaultValue = "1") Integer user_level,
			@RequestParam(value = "user_status", required = false, defaultValue = "1") Integer user_status)
			throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		logger.info("*****接收参数****** module={} login_name={} password={} user_level={} user_status={}", module,
				login_name, password, user_level, user_status);

		if (StringUtils.isAnyBlank(module, login_name, password)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}

		if ("insert".equals(module)) {
			List<Map<String, Object>> list = userService.findByLoginName(login_name);
			if (list != null && list.size() > 0) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1001", "", data);
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("login_name", login_name);
			paramMap.put("password", password);
			paramMap.put("user_level", user_level);
			paramMap.put("user_status", user_status);
			int result = userService.insert(paramMap);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
		} else if ("userlist".equals(module)) {

		} else if ("update".equals(module)) {

		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
