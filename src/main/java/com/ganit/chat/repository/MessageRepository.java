package com.ganit.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganit.chat.entity.MessageEntity;
import com.ganit.chat.entity.UserEntity;



@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
	
	//all the message recieved
	public List<MessageEntity>  findByTo(UserEntity userId);
	
	
	//all the message sent
	public List<MessageEntity>  findByFrom(UserEntity userId);
	
	// all the message  recieved from perticular user
	public List<MessageEntity>  findByToAndFrom(UserEntity fromUserId,UserEntity toUserId);


	
}
