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
import com.china180.service.MsgService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class MsgController extends BaseController {

	@Resource
	private MsgService msgService;

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/MsgManager")
	public Map<String, Object> msgManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		String module = request.getParameter("module");
		logger.info("*****接收参数****** module={}", module);

		if (StringUtils.isAnyBlank(module)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}
		if ("msg_list_by_user".equals(module)) {
			String userid = request.getParameter("userid");
			if (StringUtils.isAnyBlank(userid)) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
			}
			List<Map<String, Object>> list = msgService.findMsgByUser(userid);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", list);
		} else if ("read_msg".equals(module)) {
			String msg_id = request.getParameter("msg_id");
			if (StringUtils.isAnyBlank(msg_id)) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
			}
			int result = msgService.readMsg(msg_id);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
		} else if ("list_all".equals(module)) {
			String sSearch = request.getParameter("sSearch");
			String iDisplayLength = request.getParameter("iDisplayLength");
			String iDisplayStart = request.getParameter("iDisplayStart");
			if (StringUtils.isNotBlank(sSearch)) {
				sSearch = new String(sSearch.getBytes("iso-8859-1"), Constant.ENCODING_UTF8);
			}
			if (StringUtils.isBlank(iDisplayLength)) {
				iDisplayLength = "10";
			}
			if (StringUtils.isBlank(iDisplayStart)) {
				iDisplayStart = "1";
			}

			// 只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
			PageHelper.startPage(Integer.parseInt(iDisplayStart), Integer.parseInt(iDisplayLength));
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("msg_title", sSearch);
			List<Map<String, Object>> list = msgService.findMsg(paramMap);
			// 需要把Page包装成PageInfo对象才能序列化
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
			
			JSONArray aaData = new JSONArray();
			for (Map<String, Object> map : list) {
				JSONArray record = new JSONArray();
				record.add(map.get("msg_id"));
				record.add(map.get("login_name"));
				record.add(map.get("msg_level"));
				record.add(map.get("msg_title"));
				record.add(map.get("msg_info"));
				record.add(map.get("be_read"));
				
				aaData.add(record);
			}
			returnMap.put("sEcho", request.getParameter("sEcho"));
			returnMap.put("iTotalRecords", pageInfo.getTotal());
			returnMap.put("iTotalDisplayRecords", pageInfo.getTotal());
			returnMap.put("aaData", aaData);
			returnMap.put(Constant.RESULTCODE, Constant.SUCCESS);
			returnMap.put(Constant.STATUS, Constant.OK);
			return returnMap;
		} else if ("delete".equals(module)) {
			String msg_id = StringUtils.defaultString(request.getParameter("target"), "");
			int result = msgService.delete(msg_id);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
