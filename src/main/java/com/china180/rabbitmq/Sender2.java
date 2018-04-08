package com.china180.rabbitmq;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender2 {

	protected Logger logger = LogManager.getLogger(Sender2.class);

	@Resource(name = "secondRabbitTemplate")
	private RabbitTemplate secondRabbitTemplate;

	@Value("${spring.rabbitmq.second.queue}")
	private String queue2;

	public void send(String message) {
		logger.debug("=============[Sender2]Sender2 of [{}]: {}", queue2, message);
		this.secondRabbitTemplate.convertAndSend(queue2, message);
	}

}