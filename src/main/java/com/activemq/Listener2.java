package com.activemq;

import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener2 {

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@JmsListener(destination = "outbound.topic")
	public void receiveMessageFromTopic(final Message stringMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + stringMessage);
		String response = null;
		if (stringMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) stringMessage;
			messageData = textMessage.getText();
			response = "Hello This is Topic " + messageData;
			System.out.println(response);
		}
	}

}
