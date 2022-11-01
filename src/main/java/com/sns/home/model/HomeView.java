package com.sns.home.model;

import java.util.List;

import com.sns.follow.model.Followee;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class HomeView {
	
	// 홈화면이 어느 유저인지
	private User user;

	// 유저가 기재한 포스트 리스트
	private List<Post> postList;

	// 팔로잉 수
	private int followingCount;
	
	// 팔로워 수
	private int followerCount;
	
	// 내가 팔로우 했는지
	private boolean followOrNot;
	
	//팔로워 정보
	private List<User> follow;
	
	//팔로우한 사람 정보
	private List<Followee> followeeList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public boolean getFollowOrNot() {
		return followOrNot;
	}

	public void setFollowOrNot(boolean followOrNot) {
		this.followOrNot = followOrNot;
	}

	public List<User> getFollow() {
		return follow;
	}

	public void setFollow(List<User> follow) {
		this.follow = follow;
	}

	public List<Followee> getFolloweeList() {
		return followeeList;
	}

	public void setFolloweeList(List<Followee> followeeList) {
		this.followeeList = followeeList;
	}
	
	
}
