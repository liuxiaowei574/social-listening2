package com.china180.schedule;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.china180.rabbitmq.Sender;
import com.china180.rabbitmq.Sender2;
import com.china180.service.ProjectService;
import com.china180.thrift.ThriftClient;

/**
 * 定时任务
 * 
 * @author shaowei.liu
 *
 */
@Component
public class QuartzTask {
	protected Logger logger = LogManager.getLogger(QuartzTask.class);

	private static final String SYMBOL_CHAR = "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]";

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Sender sender;

	@Autowired
	private Sender2 sender2;

	@Autowired
	private ThriftClient thriftClient;

	/**
	 * 定时任务
	 * <p>
	 * 发送任务给引擎，通过RPC
	 */
//	@Scheduled(cron = "${scheduller.engine.task.cron}")
	public void sendTasksToEngine() {
		List<Map<String, Object>> list = projectService.findEngineTask();
		if (list == null) {
			return;
		}
		for (Map<String, Object> map : list) {
			try {
				sendToEngine(map);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("send task exception", e);
			}
		}
	}

	/**
	 * 定时任务
	 * <p>
	 * 发送任务给爬虫，通过RabbitMQ
	 */
//	@Scheduled(cron = "${scheduller.spider.task.cron}")
	public void sendTasksToSpider() {
		List<Map<String, Object>> list = projectService.findSpiderTask();
		if (list == null) {
			return;
		}
		for (Map<String, Object> map : list) {
			try {
				sendToSpider(map);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("send task exception", e);
			}
		}
	}

	/**
	 * 发送爬虫任务
	 * 
	 * @param map
	 */
	private void sendToSpider(Map<String, Object> map) {
		String message = String.valueOf(map.get("brandProduct"));
		logger.info("Message:{}", message);
		sender.send(message);
		sender2.send(message);
	}

	/**
	 * 发送引擎任务
	 * 
	 * @param map
	 * @throws Exception 
	 */
	private void sendToEngine(Map<String, Object> map) throws Exception {
		JSONObject mainObject = new JSONObject();
		mainObject.put("project_id", map.get("project_id") + "");
		mainObject.put("parameter_list", (map.get("project_info") + "").replaceAll(SYMBOL_CHAR, ""));
		mainObject.put("project_start_time", Long.parseLong(String.valueOf(map.get("start_time"))));
		mainObject.put("project_end_time", Long.parseLong(String.valueOf(map.get("start_time"))));

		thriftClient.open();
		int res1 = thriftClient.sendTaskToEngine(0, mainObject.toString());
		int res2 = thriftClient.sendTaskToEngine(1, mainObject.toString());
		logger.info("Message:{} res1:{} res2:{}", mainObject.toString(), res1, res2);

		mainObject.put("parameter_list", (map.get("project_brand") + " " + map.get("project_product")));
		int res3 = thriftClient.sendTaskToEngine(2, mainObject.toString());
		logger.info("Message:{} res3:{}", mainObject.toString(), res3);
	}

}