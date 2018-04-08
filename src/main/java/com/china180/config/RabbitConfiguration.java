package com.china180.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RabbitMQ配置
 * 
 * @author shaowei.liu
 *
 */
@Configuration
public class RabbitConfiguration {

	protected Logger logger = LogManager.getLogger(RabbitConfiguration.class);

	@Value("${spring.rabbitmq.first.queue}")
	private String queue1;

	@Value("${spring.rabbitmq.second.queue}")
	private String queue2;

	@Bean(name = "firstConnectionFactory")
	@Primary
	public ConnectionFactory firstConnectionFactory(@Value("${spring.rabbitmq.first.host}") String host,
			@Value("${spring.rabbitmq.first.port}") int port,
			@Value("${spring.rabbitmq.first.username}") String username,
			@Value("${spring.rabbitmq.first.password}") String password,
			@Value("${spring.rabbitmq.first.virtual-host}") String virtualHost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		return connectionFactory;
	}

	@Bean(name = "secondConnectionFactory")
	public ConnectionFactory secondConnectionFactory(@Value("${spring.rabbitmq.second.host}") String host,
			@Value("${spring.rabbitmq.second.port}") int port,
			@Value("${spring.rabbitmq.second.username}") String username,
			@Value("${spring.rabbitmq.second.password}") String password,
			@Value("${spring.rabbitmq.second.virtual-host}") String virtualHost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		return connectionFactory;
	}

	@Bean(name = "firstRabbitTemplate")
	@Primary
	public RabbitTemplate firstRabbitTemplate(
			@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
		RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
		return firstRabbitTemplate;
	}

	@Bean(name = "secondRabbitTemplate")
	public RabbitTemplate secondRabbitTemplate(
			@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
		RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
		return secondRabbitTemplate;
	}

	@Bean(name = "firstFactory")
	public SimpleRabbitListenerContainerFactory firstFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		return factory;
	}

	@Bean(name = "secondFactory")
	public SimpleRabbitListenerContainerFactory secondFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		return factory;
	}

	@Bean
	public Queue firstQueue() {
		logger.info("Configuration of Queue[{}] ........................", queue1);
		return new Queue(queue1);
	}

	@Bean
	public Object secondQueue() {
		logger.info("Configuration of Queue[{}] ........................", queue2);
		return new Queue(queue2);
	}
}