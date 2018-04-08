package com.china180.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.alibaba.fastjson.JSONObject;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
	JSONObject json = null;

	public static class JsonReader {
		public static JSONObject receivePost(HttpServletRequest request)
				throws IOException, UnsupportedEncodingException {

			// 读取请求内容
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			// 将json字符串转换为json对象
			JSONObject json = null;
			try {
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

	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		try {
			json = JsonReader.receivePost((HttpServletRequest) this.getRequest());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getParameter(String name) {
		if ("POST".equalsIgnoreCase(this.getMethod())) {
			try {
				return json.get(name).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.getParameter(name);
	}
}
