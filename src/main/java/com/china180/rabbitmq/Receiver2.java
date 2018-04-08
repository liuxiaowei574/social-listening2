package com.china180.rabbitmq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;

//@Component
public class Receiver2 {

	protected Logger logger = LogManager.getLogger(Receiver2.class);

	@Value("${spring.rabbitmq.second.queue}")
	private String queue2;

//	@RabbitListener(queues = "${spring.rabbitmq.second.queue}", containerFactory = "secondFactory")
//	@RabbitHandler
	public void process(Message msg) {
		String info = new String(msg.getBody());
		logger.debug("=============[Receiver2]Receiver2 of [{}]: {}", queue2, info);
	}

}