package com.sns.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowDAO {

	public int insertFollow(
			@Param("followerUserId") int followerUserId, 
			@Param("followeeUserId") int followeeUserId);
	
	public int deleteFollow(
			@Param("followerUserId") int followerUserId, 
			@Param("followeeUserId") int followeeUserId);
	
	public int selectFollowCountByFollowerUserIdOrFolloweeUserId(
			@Param("followerUserId") Integer followerUserId,
			@Param("followeeUserId") Integer followeeUserId);
	
	public List<Integer> selectFolloweeUserIdByFollowerUserId(Integer followerUserId);
	
	public List<Integer> selectFollowerUserIdByFolloweeUserId(Integer followeeUserId);
}
