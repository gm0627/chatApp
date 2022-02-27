package com.ganit.chat.model;

public class Message {
	
	Long id;
	Long from;
	Long to;
	String message;
	
	
	
	public Message() {
		super();
	}
	public Message(Long from, Long to, String message) {
		super();
		this.from = from;
		this.to = to;
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFrom() {
		return from;
	}
	public void setFrom(Long from) {
		this.from = from;
	}
	public Long getTo() {
		return to;
	}
	public void setTo(Long to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
