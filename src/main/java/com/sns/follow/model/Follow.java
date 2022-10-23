package com.sns.follow.model;

import java.util.Date;

public class Follow {
	private String id;
	private int userId_follower;
	private int userId_followed;
	private Date createdAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserId_follower() {
		return userId_follower;
	}
	public void setUserId_follower(int userId_follower) {
		this.userId_follower = userId_follower;
	}
	public int getUserId_followed() {
		return userId_followed;
	}
	public void setUserId_followed(int userId_followed) {
		this.userId_followed = userId_followed;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}