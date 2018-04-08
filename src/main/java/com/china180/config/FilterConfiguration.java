package com.china180.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.china180.filter.PostFilter;
import com.china180.filter.XssFilter;

/**
 * 自定义过滤器配置
 * 
 * @author shaowei.liu
 *
 */
@Configuration
public class FilterConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * 配置过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setOrder(1);
		registration.setFilter(xssFilter());
		registration.addUrlPatterns("/*");
		// registration.addInitParameter("paramName", "paramValue");
		registration.setName("xssFilter");
		return registration;
	}
	
	/**
	 * 配置过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean postFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setOrder(2);
		registration.setFilter(postFilter());
		registration.addUrlPatterns("/*");
		// registration.addInitParameter("paramName", "paramValue");
		registration.setName("postFilter");
		return registration;
	}

	/**
	 * 创建xssFilter
	 * 
	 * @return
	 */
	@Bean(name = "xssFilter")
	public Filter xssFilter() {
		return new XssFilter();
	}

	/**
	 * 创建postFilter
	 * 
	 * @return
	 */
	@Bean(name = "postFilter")
	public Filter postFilter() {
		return new PostFilter();
	}

}