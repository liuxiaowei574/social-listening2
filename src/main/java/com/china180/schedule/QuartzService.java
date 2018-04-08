package com.china180.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.china180.rabbitmq.Sender;
import com.china180.rabbitmq.Sender2;

@Component
public class QuartzService {

	@Autowired
	private Sender sender;

	@Autowired
	private Sender2 sender2;

	// @Scheduled(cron = "${scheduller.engine.task.cron}")
	public void sendTasksToEngineForAllProject() {
		System.out.println("[SendTasksToEngineForAllProject]now time:"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	// @Scheduled(cron = "${scheduller.spider.task.cron}")
	public void sendTasksToSpiderForAllProject() {
		System.out.println("[SendTasksToSpiderForAllProject]now time:"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		String message = "";
		sender.send(message);
		sender2.send(message);
	}

}