package com.china180.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动时自动运行
 * 
 * @author shaowei.liu
 *
 */
@Component
@Order(value = 1)
public class MyAutoRunner implements ApplicationRunner {

	protected Logger logger = LogManager.getLogger(MyAutoRunner.class);

	@Override
	public void run(ApplicationArguments var1) throws Exception {
		logger.info("================MyAutoRunner class will be execute when the project was started!");
		// TODO something
	}

}