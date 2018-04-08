package com.china180.rabbitmq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;

//@Component
public class Receiver {

	protected Logger logger = LogManager.getLogger(Receiver.class);

	@Value("${spring.rabbitmq.first.queue}")
	private String queue1;

//	@RabbitListener(queues = "${spring.rabbitmq.first.queue}", containerFactory = "firstFactory")
//	@RabbitHandler
	public void process(Message msg) {
		String info = new String(msg.getBody());
		logger.debug("=============[Receiver]Receiver of [{}]: {}", queue1, info);
	}

}