package com.china180.rabbitmq;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	protected Logger logger = LogManager.getLogger(Sender.class);

	@Resource(name = "firstRabbitTemplate")
	private RabbitTemplate firstRabbitTemplate;

	@Value("${spring.rabbitmq.first.queue}")
	private String queue1;

	public void send(String message) {
		logger.debug("=============[Sender]Sender of [{}]: {}", queue1, message);
		this.firstRabbitTemplate.convertAndSend(queue1, message);
	}

}