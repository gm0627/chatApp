package com.ganit.chat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE_TABLE")
public class MessageEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long ID;

	@OneToOne
    @JoinColumn(referencedColumnName="id")
	UserEntity from;
	
	@OneToOne
    @JoinColumn(referencedColumnName="id")
	UserEntity to;
	
	@Column
	String message;
	

	public MessageEntity() {
		super();
	}


	public MessageEntity(UserEntity from, UserEntity to, String message) {
		super();
		this.from = from;
		this.to = to;
		this.message = message;
	}
	

	public Long getId() {
		return ID;
	}

	public void setId(Long id) {
		this.ID = id;
	}

	public UserEntity getFrom() {
		return from;
	}

	public void setFrom(UserEntity from) {
		this.from = from;
	}

	public UserEntity getTo() {
		return to;
	}

	public void setTo(UserEntity to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
