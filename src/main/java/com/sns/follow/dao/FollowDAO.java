package com.sns.follow.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowDAO {

	public int insertFollow(
			@Param("followerUserId") int followerUserId, 
			@Param("followedUserId") int followedUserId);
	
	public int deleteFollow(
			@Param("followerUserId") int followerUserId, 
			@Param("followedUserId") int followedUserId);
}
