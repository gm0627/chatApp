package com.ganit.chat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganit.chat.entity.MessageEntity;
import com.ganit.chat.entity.UserEntity;
import com.ganit.chat.exception.EntityNotFoundException;
import com.ganit.chat.model.Message;
import com.ganit.chat.repository.MessageRepository;
import com.ganit.chat.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messagerRepository;
	
	@Autowired
	UserRepository userRepository;


	public List<Message> findAllSent(Long userId) {
		// TODO Auto-generated method stub
		List<MessageEntity> messages = null;
		UserEntity user=userRepository.getById(userId);
		if(user!=null) {
		messages=messagerRepository.findByFrom(user);
		}
		return messages.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	public List<Message> findAllRecieved(Long userId) {
		List<MessageEntity> messages = null;
		UserEntity user=userRepository.getById(userId);
		if(user!=null)
			messages=messagerRepository.findByTo(user);
		
		return messages.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	 public List<Message> findAllRecievedby(Long fromUserId, Long toUserId) {
		 List<MessageEntity> messages=null;
		 try {
			 UserEntity from=userRepository.getById(fromUserId);
			 UserEntity to=userRepository.getById(toUserId);
			 if(from!=null && to!=null) 
			  messages= messagerRepository.findByToAndFrom(from, to);
		} catch (Exception e) {
			throw new EntityNotFoundException("User not found");
		}
		 
		return messages.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}
	
public void updateMessage( Message message) {
		
		UserEntity from =userRepository.getById(message.getFrom());
		UserEntity to =userRepository.getById(message.getTo());
		MessageEntity newMessage= new MessageEntity(from, to, message.getMessage());
		messagerRepository.save(newMessage);
	}

public Message convertEntityToDTO(MessageEntity message) {
	Message msg= new Message(message.getFrom().getId(),message.getTo().getId(),message.getMessage());
	msg.setId(message.getId());
	return msg;
}

}
