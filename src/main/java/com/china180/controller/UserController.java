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

import com.china180.service.UserService;
import com.china180.util.ResponseMapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/UserManager/listAll")
	public Map<String, Object> listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		pageNum = StringUtils.isNotBlank(pageNum) ? pageNum : "1";
		pageSize = StringUtils.isNotBlank(pageSize) ? pageSize : "1";

		// 只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		List<Map<String, Object>> userList = userService.findAll();
		// 需要把Page包装成PageInfo对象才能序列化
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(userList);
		returnMap.put("pageInfo", pageInfo);
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/UserManager")
	public Map<String, Object> userManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();

		String module = request.getParameter("module");
		String login_name = request.getParameter("login_name");
		String password = request.getParameter("password");
		String user_level = request.getParameter("user_level");
		String user_status = request.getParameter("user_status");
		logger.info("*****接收参数****** module={} login_name={} password={} user_level={} user_status={}", module,
				login_name, password, user_level, user_status);

		if (StringUtils.isAnyBlank(module, login_name, password)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}
		user_level = StringUtils.isNotBlank(user_level) ? user_level : "1";
		user_status = StringUtils.isNotBlank(user_status) ? user_status : "1";

		if ("insert".equals(module)) {
			List<Map<String, Object>> list = userService.findByLoginName(login_name);
			if (list != null && list.size() > 0) {
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
		} else if ("userlist".equals(module)) {

		} else if ("update".equals(module)) {

		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
