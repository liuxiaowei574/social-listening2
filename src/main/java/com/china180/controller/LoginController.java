package com.china180.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.china180.enums.UserStatusEnum;
import com.china180.service.UserService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;

@RestController
public class LoginController extends BaseController {

	@Resource
	private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login")
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		String login_name = request.getParameter("login_name");
		String password = request.getParameter("password");
		String logout = request.getParameter("logout");
		logger.info("*****接收参数****** login_name={} password={} logout={}", login_name, password, logout);
		
		HttpSession session = request.getSession();
		logger.info("sessionId:" + session.getId());
		boolean already_login = false;
		if (StringUtils.isNotBlank(logout)) {
			// 登出
			session.removeAttribute(Constant.SESSION_USER);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else {
			if (StringUtils.isBlank(password)) {
				// 检查登录状态
				Object obj = session.getAttribute(Constant.SESSION_USER);
				if (obj != null) {
					already_login = true;
				} else {
					already_login = false;
				}
			} else {
				// 登录
				List<Map<String, Object>> list = userService.login(login_name);
				if (list == null || list.size() < 1) {
					return ResponseMapUtil.setDefultError(returnMap, Constant.ERROR, "", data);
				}
				Map<String, Object> user = list.get(0);
				if (!password.equals(String.valueOf(user.get("password")))) {
					return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
				}
				if (!UserStatusEnum.Normal.getText().equals(String.valueOf(user.get("user_status")))) {
					return ResponseMapUtil.setOtherResultCode(returnMap, "1004", "", data);
				}
				already_login = true;
				data.putAll(user);
				data.remove("password");
			}

			if (!already_login) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
			}
			if (data.size() < 1) {
				Map<String, Object> user = userService.findByLoginName(login_name);
				data.putAll(user);
			}

			session.setAttribute(Constant.SESSION_USER, data);
			session.setMaxInactiveInterval(3600 * 2);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
