package com.sns.follow.dao;

import java.util.List;

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
	
	public int selectFollowCountByFollowerUserIdOrFollowedUserId(
			@Param("followerUserId") Integer followerUserId,
			@Param("followedUserId") Integer followedUserId);
	
	public List<Integer> selectFollowUserIdListByFollowedUserId(int followedUserId);
	
	public List<Integer> selectFolloweeUserIdListByFollowerUserId(int followerUserId);
}
