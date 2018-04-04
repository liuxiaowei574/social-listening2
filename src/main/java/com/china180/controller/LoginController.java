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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.china180.enums.UserStatusEnum;
import com.china180.service.UserService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;
import com.china180.vo.User;

@RestController
public class LoginController extends AbstractController {

	@Resource
	private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login")
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "login_name", required = false) String login_name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "logout", required = false) String logout) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		logger.info("*****接收参数****** login_name={} password={} logout={}", login_name, password, logout);

		HttpSession session = request.getSession();
		boolean already_login = false;
		if (StringUtils.isNotBlank(logout)) {
			// 登出
			session.removeAttribute(Constant.SESSION_USER);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else {
			if (StringUtils.isBlank(password)) {
				// 检查登录状态
				Object obj = (String) session.getAttribute(Constant.SESSION_USER);
				if (obj != null) {
					already_login = true;
				} else {
					already_login = false;
				}
			} else {
				// 登录
				List<User> list = userService.login(login_name);
				if (list == null || list.size() < 1) {
					return ResponseMapUtil.setDefultError(returnMap, Constant.ERROR, "", data);
				}
				User user = list.get(0);
				if (!password.equals(user.getPassword())) {
					return ResponseMapUtil.setOtherResultCode(returnMap, "6", "", data);
				}
				if (!UserStatusEnum.Normal.getText().equals(String.valueOf(user.getUserStatus()))) {
					return ResponseMapUtil.setOtherResultCode(returnMap, "7", "", data);
				}
				already_login = true;
				data.put("user_id", user.getUserId());
				data.put("loginname", user.getLoginName());
				data.put("user_status", user.getUserStatus());
				data.put("user_level", user.getUserLevel());
				data.put("user_pic", user.getUserPic());
			}

			if (!already_login) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "5", "", data);
			}
			if (data.size() < 1) {
				List<Map<String, Object>> list = userService.findByLoginName(login_name);
				data.putAll(list.get(0));
			}

			session.setAttribute(Constant.SESSION_USER, data);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
