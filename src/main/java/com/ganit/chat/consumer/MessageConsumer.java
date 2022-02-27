package com.ganit.chat.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ganit.chat.model.Message;
import com.ganit.chat.service.MessageService;
import com.ganit.chat.util.RabbitMQInfo;

@Component
public class MessageConsumer {

	@Autowired
	MessageService service;
	
	@RabbitListener(queues = RabbitMQInfo.QUEUE)
	public void sendMessage(Message message) {
		service.updateMessage( message);
		
	}
}
