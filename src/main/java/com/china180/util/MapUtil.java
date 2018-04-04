package com.china180.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapUtil {
	private static Logger logger = LogManager.getLogger("WOPAY");

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertBean2Map(Object bean) {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	/**
	 * 设置默认错误
	 * 
	 * @param map
	 * @param code
	 * @param codeMsg
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map setDefultError(Map map, String code, String codeMsg) {
		map.put(Constant.RESULTCODE, Constant.ERROR);
		code = StringUtils.isBlank(code) ? Constant.ERROR : code;
		String sysCodeMsg = SystemErrorCodePropertiesUtil.getProps(code);
		map.put(Constant.REASON, sysCodeMsg == null ? codeMsg : sysCodeMsg);
		logger.info("return Map =" + map);
		return map;
	}

	/**
	 * 设置默认成功
	 * 
	 * @param map
	 * @param codeMsg
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map setDefultSuccess(Map map, String codeMsg) {
		map.put(Constant.RESULTCODE, Constant.SUCCESS);
		map.put(Constant.REASON, codeMsg == null ? "" : codeMsg);
		logger.info("return Map =" + map);
		return map;
	}

	/**
	 * 设置其他错误类型
	 * 
	 * @author guojn
	 * @param map
	 * @param codeMsg
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map setOtherResultCode(Map map, String code, String codeMsg) {
		map.put(Constant.RESULTCODE, code);
		String sysCodeMsg = SystemErrorCodePropertiesUtil.getProps(code);
		map.put(Constant.REASON, sysCodeMsg == null ? codeMsg : sysCodeMsg);
		logger.info("return Map =" + map);
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isSuccess(Map inOrderMap) {
		if (Constant.SUCCESS.equals(inOrderMap.get(Constant.RESULTCODE))) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static String getMapValue(Map inMap, String key) {
		if (inMap.get(key) == null || "".equals(inMap.get(key)) || "null".equals(inMap.get(key))) {
			return "";
		} else {
			return inMap.get(key) + "";
		}
	}
}
