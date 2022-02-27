package com.ganit.chat.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ganit.chat.exception.EntityNotFoundException;
import com.ganit.chat.model.Message;
import com.ganit.chat.service.MessageService;
import com.ganit.chat.util.RabbitMQInfo;
import com.ganit.chat.util.ResponseMessage;

@RestController
public class MessageController {

	@Autowired
	MessageService service;
	
	@Autowired
	RabbitTemplate template;


	@PostMapping("/message")
	public ResponseMessage sendMessage(@RequestHeader(required = true)Long userId ,@RequestBody Message message) {
		message.setFrom(userId);
		template.convertAndSend(RabbitMQInfo.EXCHANGE,RabbitMQInfo.ROUTING_KEY,message);
		//service.updateMessage(userId,message);
		return new ResponseMessage(String.format("Message sent from  user :%d to user %d successfully",userId,message.getTo()));
	}
	
	
	@GetMapping("/messages/in")
	@ResponseBody
	public List<Message> getAllRecievedMessages(@RequestHeader(required = true)Long userId) {
		 
		List<Message> messages=service.findAllRecieved(userId);
		if(messages.isEmpty()) {
			throw new EntityNotFoundException("User not found!");
		}
		return messages;
	}
	
	@GetMapping("/messages/out")
	@ResponseBody
	public List<Message> getAllSentMessages(@RequestHeader(required = true)Long userId) {
		 
		List<Message> messages=service.findAllSent(userId);
		if(messages.isEmpty()) {
			throw new EntityNotFoundException("User not found!");
		}
		return messages;
	}
	
	@GetMapping("/messages/in/{fromUserId}")
	@ResponseBody
	public List<Message> getAllRecievedMessagesbyUserID(@RequestHeader(required = true)Long userId,@PathVariable(required = true)Long fromUserId) {
		 
		List<Message> messages=service.findAllRecievedby(userId, fromUserId);
		if(messages.isEmpty()) {
			throw new EntityNotFoundException("User not found!");
		}
		return messages;
	}
}
