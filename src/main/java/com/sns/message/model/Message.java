package com.sns.message.model;

import java.util.Date;

public class Message {
	private int id;
	private int chatroomId;
	private int userIdSender;
	private int userIdReceiver;
	private String content;
	private Date createdAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChatroomId() {
		return chatroomId;
	}
	public void setChatroomId(int chatroomId) {
		this.chatroomId = chatroomId;
	}
	public int getUserIdSender() {
		return userIdSender;
	}
	public void setUserIdSender(int userIdSender) {
		this.userIdSender = userIdSender;
	}
	public int getUserIdReceiver() {
		return userIdReceiver;
	}
	public void setUserIdReceiver(int userIdReceiver) {
		this.userIdReceiver = userIdReceiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
