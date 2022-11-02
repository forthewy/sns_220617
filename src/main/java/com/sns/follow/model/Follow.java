package com.sns.follow.model;

import java.util.Date;

public class Follow {
	private String id;
	private int userIdFollower;
	private int userIdFollowee;
	private Date createdAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getUserIdFollower() {
		return userIdFollower;
	}
	public void setUserIdFollower(int userIdFollower) {
		this.userIdFollower = userIdFollower;
	}
	
	public int getUserIdFollowee() {
		return userIdFollowee;
	}
	public void setUserIdFollowee(int userIdFollowee) {
		this.userIdFollowee = userIdFollowee;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}