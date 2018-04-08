package com.china180.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class PostHttpServletRequestWrapper extends HttpServletRequestWrapper {
	protected static Logger logger = LogManager.getLogger(PostHttpServletRequestWrapper.class);

	HttpServletRequest orgRequest = null;
	String method = null;
	JSONObject json = null;

	public static class JsonReader {
		public static JSONObject receivePost(HttpServletRequest request)
				throws IOException, UnsupportedEncodingException {
			// 读取请求内容
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			// 将json字符串转换为json对象
			JSONObject json = null;
			try {
				logger.debug(sb);
				if (sb.toString().length() > 0) {
					json = JSONObject.parseObject(sb.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			br.close();
			return json;
		}
	}

	public PostHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
		method = request.getMethod();
		try {
			json = JsonReader.receivePost((HttpServletRequest) this.getRequest());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getParameter(String name) {
		if ("POST".equalsIgnoreCase(method)) {
			try {
				if (json == null) {
					return null;
				}
				return json.getString(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orgRequest.getParameter(name);
	}

	/**
	 * 获取最原始的request
	 * 
	 * @return
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request的静态方法
	 * 
	 * @return
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof PostHttpServletRequestWrapper) {
			return ((PostHttpServletRequestWrapper) req).getOrgRequest();
		}

		return req;
	}

}