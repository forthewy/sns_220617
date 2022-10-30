package com.sns.follow.model;

import java.util.List;

import com.sns.user.model.User;

public class FollowView {
	
	// 유저리스트...팔로워든 팔로잉이던...
	private List<User> userList;

	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	

}
