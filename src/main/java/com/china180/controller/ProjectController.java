package com.china180.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.china180.service.ProjectService;
import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;
import com.china180.vo.Project;

@RestController
public class ProjectController extends BaseController {

	private static final String BAIDU_BAIKE = "https://baike.baidu.com/search/word?word=";

	@Resource
	private ProjectService projectService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ProjectManager")
	public Map<String, Object> projectManager(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "module", required = false) String module) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> data = new HashMap<String, Object>();
		logger.info("*****接收参数****** module={}", module);

		if (StringUtils.isAnyBlank(module)) {
			return ResponseMapUtil.setOtherResultCode(returnMap, "1002", "", data);
		}

		debugRequestParameters(request);
		if ("query_project_info_from_baike".equals(module)) {
			String project_brand = request.getParameter("project_brand");
			String project_product = request.getParameter("project_product");
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
			String project_id = request.getParameter("project_id");
			String project_name = request.getParameter("project_name");
			String project_type = request.getParameter("project_type");
			String project_brand = null;
			String project_product = null;
			if ("1".equals(project_type)) {
				project_brand = request.getParameter("project_brand");
				project_product = request.getParameter("project_product");
			}
			String project_info = request.getParameter("project_info");
			String project_key_words = request.getParameter("project_key_words");
			Timestamp start_time = new Timestamp(Long.parseLong(request.getParameter("start_time")));
			Timestamp end_time = new Timestamp(Long.parseLong(request.getParameter("end_time")));
			String project_source = request.getParameter("project_source");
			if (StringUtils.isNotBlank(project_source)) {
				project_source = project_source.replaceAll(" ", "+");
			}

			List<Map<String, Object>> list = projectService.findById(project_id);
			if (list == null || list.size() < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "1003", "", data);
			}
			Map<String, Object> map = list.get(0);
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
			String project_name = request.getParameter("project_name");
			String project_type = request.getParameter("project_type");
			Map userInfo = (Map) request.getSession().getAttribute(Constant.SESSION_USER);
			if (userInfo == null || userInfo.size() < 1) {
				return ResponseMapUtil.setOtherResultCode(returnMap, "2002", "", data);
			}
			String user_id = String.valueOf(userInfo.get("user_id"));
			String project_brand = null;
			String project_product = null;
			if ("1".equals(project_type)) {
				project_brand = request.getParameter("project_brand");
				project_product = request.getParameter("project_product");
			}
			String project_info = request.getParameter("project_info");
			String project_key_words = request.getParameter("project_key_words");
			Timestamp start_time = new Timestamp(Long.parseLong(request.getParameter("start_time")));
			Timestamp end_time = new Timestamp(Long.parseLong(request.getParameter("end_time")));
			String project_source = request.getParameter("project_source");
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
			String user_id = request.getParameter("user_id");
			String project_type = request.getParameter("project_type");
			List<Map<String, Object>> list = projectService.findByUser(user_id, project_type);
			return ResponseMapUtil.setDefultSuccess(returnMap, "", list);
		}

		return ResponseMapUtil.setDefultSuccess(returnMap, "", data);
	}

}
