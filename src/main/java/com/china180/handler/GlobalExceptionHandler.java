package com.china180.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.china180.util.Constant;
import com.china180.util.ResponseMapUtil;

/**
 * 统一异常处理
 * 
 * @author shaowei.liu
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	protected Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		e.printStackTrace();
		logger.error(e);
		return ResponseMapUtil.setDefultError(new HashMap(), Constant.ERROR, "", "");
	}

}
