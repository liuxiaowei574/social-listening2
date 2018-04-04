package com.china180.util;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @Description获取错误提示语的工具类
 * @author sunkai
 * @date 2016年11月7日
 */
public class SystemErrorCodePropertiesUtil {

	private static Logger logger = LogManager.getLogger("WOPAY");
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("SystemErrorInfo.properties"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	public static String getProps(String key) {
		return props.getProperty(key);
	}
	/**
	 * 如果能取得转化后结果返回转化结果，如果没有则透传
	 * @param key
	 * @param msg
	 * @return
	 */
	public static String getProps(String key,String msg) {
		String codemsg=props.getProperty(key);
		return codemsg==null?msg:codemsg;
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
}
