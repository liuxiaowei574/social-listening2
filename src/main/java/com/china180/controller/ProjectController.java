package com.china180.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.china180.service.ProjectService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;
import com.china180.vo.Project;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class ProjectController extends BaseController {

	private static final String BAIDU_BAIKE = "https://baike.baidu.com/search/word?word=";

	String[] sourceNameList = { "", "微博\t", "JD快报\t", "JD电商\t", "天猫超市\t" };

	@Resource
	private ProjectService projectService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ProjectManager")
	public Map<String, Object> projectManager(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		String module = StringUtils.defaultString(request.getParameter("module"), "");
		logger.info("*****接收参数****** module={}", module);

		if (StringUtils.isAnyBlank(module)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}

		if ("query_project_info_from_baike".equals(module)) {
			String project_brand = StringUtils.defaultString(request.getParameter("project_brand"), "");
			String project_product = StringUtils.defaultString(request.getParameter("project_product"), "");
			if (StringUtils.isAnyBlank(project_brand, project_product)) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
			}
			// project_brand = new String(project_brand.getBytes("ISO-8859-1"),
			// Constant.ENCODING_UTF8);
			// project_product = new
			// String(project_product.getBytes("ISO-8859-1"),
			// Constant.ENCODING_UTF8);
			String urlStr = BAIDU_BAIKE + project_brand + project_product;
			String html = Jsoup.connect(urlStr).get().body().select("div.para").text().replace(" ", "")
					.replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
			if (StringUtils.isBlank(html)) {
				html = "没有从百度百科搜索到项目商品相关介绍";
			}
			logger.debug("baidu_baike html:{}", html);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", html);
		} else if ("update_by_owner".equals(module)) {
			Map userInfo = (Map) request.getSession().getAttribute(Constant.SESSION_USER);
			if (userInfo == null || userInfo.size() < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
			}
			String user_id = String.valueOf(userInfo.get("user_id"));
			String project_id = StringUtils.defaultString(request.getParameter("project_id"), "");
			String project_name = StringUtils.defaultString(request.getParameter("project_name"), "");
			String project_type = StringUtils.defaultString(request.getParameter("project_type"), "");
			String project_brand = null;
			String project_product = null;
			if ("1".equals(project_type)) {
				project_brand = StringUtils.defaultString(request.getParameter("project_brand"), "");
				project_product = StringUtils.defaultString(request.getParameter("project_product"), "");
			}
			String project_info = StringUtils.defaultString(request.getParameter("project_info"), "");
			String project_key_words = StringUtils.defaultString(request.getParameter("project_key_words"), "");
			Timestamp start_time = new Timestamp(
					Long.parseLong(StringUtils.defaultString(request.getParameter("start_time"), "0")));
			Timestamp end_time = new Timestamp(
					Long.parseLong(StringUtils.defaultString(request.getParameter("end_time"), "0")));
			String project_source = StringUtils.defaultString(request.getParameter("project_source"), "");
			if (StringUtils.isNotBlank(project_source)) {
				project_source = project_source.replaceAll(" ", "+");
			}

			Map<String, Object> map = projectService.findById(project_id);
			if (map == null || map.size() < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			if (!String.valueOf(map.get("project_user_belong")).equals(user_id)) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "3001", "", data);
			} else if (Integer.parseInt(String.valueOf(map.get("project_status"))) > 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "3002", "", data);
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_name", project_name);
			paramMap.put("project_type", project_type);
			paramMap.put("project_product", project_product);
			paramMap.put("project_brand", project_brand);
			paramMap.put("project_info", project_info);
			paramMap.put("project_key_words", project_key_words);
			paramMap.put("project_source", project_source);
			paramMap.put("start_time", start_time);
			paramMap.put("end_time", end_time);
			paramMap.put("project_id", project_id);
			paramMap.put("project_create_time", new Timestamp(System.currentTimeMillis()));
			int result = projectService.update(paramMap);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
		} else if ("insert".equals(module)) {
			String project_name = StringUtils.defaultString(request.getParameter("project_name"), "");
			String project_type = StringUtils.defaultString(request.getParameter("project_type"), "");
			HttpSession session = request.getSession(false);
			logger.debug("sessionId:" + session.getId());
			Map userInfo = (Map) session.getAttribute(Constant.SESSION_USER);
			if (userInfo == null || userInfo.size() < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "2002", "", data);
			}
			String user_id = String.valueOf(userInfo.get("user_id"));
			String project_brand = null;
			String project_product = null;
			if ("1".equals(project_type)) {
				project_brand = StringUtils.defaultString(request.getParameter("project_brand"), "");
				project_product = StringUtils.defaultString(request.getParameter("project_product"), "");
			}
			String project_info = StringUtils.defaultString(request.getParameter("project_info"), "");
			String project_key_words = StringUtils.defaultString(request.getParameter("project_key_words"), "");
			Timestamp start_time = new Timestamp(
					Long.parseLong(StringUtils.defaultString(request.getParameter("start_time"), "0")));
			Timestamp end_time = new Timestamp(
					Long.parseLong(StringUtils.defaultString(request.getParameter("end_time"), "0")));
			String project_source = StringUtils.defaultString(request.getParameter("project_source"), "");
			String project_priority = "1";
			String project_status = "1";

			List<Map<String, Object>> list = projectService.findByUserAndProject(user_id, project_type, project_name);
			if (list != null && list.size() > 0) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "3003", "", data);
			}
			Project project = new Project();
			project.setProjectName(project_name);
			project.setProjectType(Integer.parseInt(project_type));
			project.setProjectProduct(project_product);
			project.setProjectUserBelong(user_id);
			project.setProjectBrand(project_brand);
			project.setProjectInfo(project_info);
			project.setProjectKeyWords(project_key_words);
			project.setProjectSource(project_source);
			project.setStartTime(start_time);
			project.setEndTime(end_time);
			project.setProjectPriority(Integer.parseInt(project_priority));
			project.setProjectStatus(Integer.parseInt(project_status));
			project.setProjectCreateTime(new Timestamp(System.currentTimeMillis()));
			int result = projectService.insert(project);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
		} else if ("project_list_by_user".equals(module)) {
			String user_id = StringUtils.defaultString(request.getParameter("user_id"), "");
			String project_type = StringUtils.defaultString(request.getParameter("project_type"), "");
			List<Map<String, Object>> list = projectService.findByUser(user_id, project_type);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", list);
		} else if ("project_list".equals(module)) {
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
			List<Map<String, Object>> list = projectService.findListByProjectName(sSearch);
			// 需要把Page包装成PageInfo对象才能序列化
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
			iTotalRecords = pageInfo.getTotal();

			JSONArray aaData = new JSONArray();
			for (Map<String, Object> map : list) {
				JSONArray record = new JSONArray();
				record.add(map.get("project_id"));
				record.add(map.get("project_name"));
				record.add(map.get("project_type"));
				record.add(map.get("login_name"));
				record.add(map.get("project_brand"));
				record.add(map.get("project_product"));
				String project_info = StringUtils.defaultIfBlank(String.valueOf(map.get("project_info")), "");
				if (project_info.length() > 20) {
					project_info = project_info.substring(0, 19) + "...";
				}
				record.add(project_info);
				record.add(map.get("project_key_words"));

				String project_source = StringUtils.defaultIfBlank(String.valueOf(map.get("project_source")), "");
				String[] tmp = project_source.replace("+", " ").split(" ");
				String source_list = "";
				for (int i = 0; i < tmp.length; i++) {
					source_list += sourceNameList[Integer.parseInt(tmp[i])];
				}
				record.add(source_list);
				record.add(map.get("project_create_time"));
				record.add(map.get("start_time"));
				record.add(map.get("end_time"));
				record.add(map.get("project_priority"));
				record.add(map.get("project_status"));

				aaData.add(record);
			}
			returnMap.put("sEcho", request.getParameter("sEcho"));
			returnMap.put("iTotalRecords", iTotalRecords);
			returnMap.put("iTotalDisplayRecords", iTotalRecords);
			returnMap.put("aaData", aaData);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("backbone_select".equals(module)) {
			String project_id = StringUtils.defaultString(request.getParameter("project_id"), "");
			List<Map<String, Object>> list = projectService.findListBackbone(project_id);
			if (list == null || list.size() < 1) {
				return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
			}
			Map<String, Object> map = list.get(0);
			data.put("project_name", map.get("project_name"));
			data.put("project_type", map.get("project_type"));
			data.put("user_belong", map.get("user_belong"));
			data.put("project_status", map.get("project_status"));
			data.put("project_brand", map.get("project_brand"));
			data.put("project_product", map.get("project_product"));
			data.put("project_info", map.get("project_info"));
			data.put("project_key_words", map.get("project_key_words"));
			data.put("project_source", map.get("project_source"));
			data.put("project_priority", map.get("project_priority"));
			data.put("project_create_time", map.get("project_create_time"));
			data.put("start_time", map.get("start_time"));
			data.put("end_time", map.get("end_time"));

			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		} else if ("check_update".equals(module)) {
			String project_id = StringUtils.defaultString(request.getParameter("project_id"), "");
			String project_priority = StringUtils.defaultString(request.getParameter("project_priority"), "");
			String project_status = StringUtils.defaultString(request.getParameter("project_status"), "");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("project_id", project_id);
			paramMap.put("project_priority", project_priority);
			paramMap.put("project_status", project_status);
			int result = projectService.update(paramMap);
			if (result < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
