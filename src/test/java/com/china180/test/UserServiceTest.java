package com.china180.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.china180.SocialListening2Application;
import com.china180.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SocialListening2Application.class)
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Test
	public void testInsert() {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("login_name", "zhangsan");
		paramMap.put("password", "xxxx");
		paramMap.put("user_level", "1");
		paramMap.put("user_status", "1");
		int result = service.insert(paramMap);
		System.out.println("result:" + result);
	}

	@Test
	public void testFindAll() {
		// 只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
		PageHelper.startPage(2, 4);
		List<Map<String, Object>> userList = service.findAll();
		System.out.println("userList:" + JSON.toJSONString(userList));
		// 需要把Page包装成PageInfo对象才能序列化
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(userList);
		System.out.println("pageInfo:" + JSON.toJSONString(pageInfo));
	}
}
