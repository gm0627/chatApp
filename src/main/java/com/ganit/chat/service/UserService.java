package com.ganit.chat.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ganit.chat.entity.UserEntity;
import com.ganit.chat.model.User;
import com.ganit.chat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	


	public List<User> findAll() {
		return userRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
		
		
	}


	public User findUserByID(long userId) {
		// TODO Auto-generated method stub
		Optional<UserEntity> user= userRepository.findById(userId);
		return user.map(this::convertEntityToDTO).orElse(null);
	}

	public void updateUser(User user) {
		userRepository.save(convertDTOToEntity(user));
	}


	public void createUserByNickName(String nickName) {
		
		UserEntity user=new UserEntity();
		user.setNickName(nickName);
		user.setName("Anonymous");
		userRepository.save(user);
		
	}
	

	
	public User convertEntityToDTO(UserEntity entity) {
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		User user= new User(entity.getName(),entity.getNickName());
		user.setId(entity.getId());
		//user=modelMapper.map(entity,User.class);
		return user;
		
	}
	public UserEntity convertDTOToEntity(User user) {
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserEntity entity= new UserEntity(user.getName(),user.getNickName());
		///entity=modelMapper.map(user,UserEntity.class);
		
		return entity;
		
	}


	
	
	
	
}
