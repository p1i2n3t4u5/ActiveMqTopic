package com.activemq;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqTopicApplicationTests {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Listener listener;

	@Autowired
	private Listener2 listener2;

	@Test
	public void sendToTopic() throws InterruptedException {
		jmsTemplate.convertAndSend("inbound.topic", " {\"name\":\"John\"}");
		listener.getLatch().await(10000, TimeUnit.MILLISECONDS);
		listener2.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

	

}
