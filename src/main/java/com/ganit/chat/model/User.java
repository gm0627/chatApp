package com.ganit.chat.model;

public class User {

	Long id;
	String name;
	String nickName;
	
	public User() {
		super();
	}
	public User(String name, String nickName) {
		super();
		this.name = name;
		this.nickName = nickName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
