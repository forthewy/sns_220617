package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public boolean existLike( // existLike.. 보통 언제쓰는 거지...
			@Param("postId") int postId,
			@Param("userId")int userId);
	
	
	public int deleteLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId")int userId);
	
	public int insertLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId")int userId);
	
	public int selectCountLikeByPostId(int postId); 
}
