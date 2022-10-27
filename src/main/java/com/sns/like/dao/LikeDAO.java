package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public int selectLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId")int userId);
	
	
	public int deleteLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId")int userId);
	
	public int insertLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId")int userId);
}
