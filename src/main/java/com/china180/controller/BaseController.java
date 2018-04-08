package com.china180.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 控制器基类
 * 
 * @author shaowei.liu
 *
 */
public abstract class BaseController {
	protected Logger logger = LogManager.getLogger(BaseController.class);

	/**
	 * debug输出入参内容，<b>不适用于流方式</b>
	 * 
	 * @param request
	 */
	protected void debugRequestParameters(HttpServletRequest request) {
		if (!logger.isDebugEnabled()) {
			return;
		}
		StringBuilder sb = createRequestParamStr(request);
		String className = this.getClass().getSimpleName();
		logger.debug("=========请求参数[" + className + "]:" + sb.toString());
	}

	/**
	 * info输出入参内容，<b>不适用于流方式</b>
	 * 
	 * @param request
	 */
	protected void infoRequestParameters(HttpServletRequest request) {
		if (!logger.isInfoEnabled()) {
			return;
		}
		StringBuilder sb = createRequestParamStr(request);
		String className = this.getClass().getSimpleName();
		logger.info("=========请求参数[" + className + "]:" + sb.toString());
	}

	/**
	 * 创建入参字符串，<b>不适用于流方式</b>
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static StringBuilder createRequestParamStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Map map = request.getParameterMap();
		Set entrySet = map.entrySet();
		for (Iterator itr = entrySet.iterator(); itr.hasNext();) {
			Map.Entry me = (Map.Entry) itr.next();
			String ok = (String) me.getKey(); // 获取参数名
			Object ov = me.getValue(); // 获取参数值
			String value = null;
			if (ov instanceof String[]) {
				value = ((String[]) ov)[0];
			} else {
				if (ov != null) {
					value = String.valueOf(ov);
				}
			}
			sb.append(" ").append(ok).append("=").append(value);
		}
		return sb;
	}

}
