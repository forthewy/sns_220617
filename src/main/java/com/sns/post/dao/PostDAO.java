package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imgPath") String imagePath);
	
	public List<Post> selectPostList();
	
	public List<Post> selectPostListByUserId(int userId);
	
	public Post selectPostByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId
			);
	
	public int deletePostByPostId(int postId);
}
