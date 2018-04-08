package com.china180.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.china180.SocialListening2Application;
import com.china180.rabbitmq.Sender;
import com.china180.rabbitmq.Sender2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SocialListening2Application.class)
public class RabbitMQTest {

	@Autowired
	private Sender sender;

	@Autowired
	private Sender2 sender2;

	@Test
	public void hello() throws Exception {
		String context = "hello2 " + new Date();
		sender.send(context);
	}

	@Test
	public void hello2() throws Exception {
		String context = "hello2 " + new Date();
		sender2.send(context);
	}
}