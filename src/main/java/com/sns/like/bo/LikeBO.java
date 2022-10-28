package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int getLikeByPostIdAndUserId(int postId, int userId) {
		return likeDAO.selectLikeByPostIdAndUserId(postId, userId);
	}

	public int deleteLikeByPostIdAndUserId(int postId, int userId) {
		return likeDAO.deleteLikeByPostIdAndUserId(postId, userId);
	}
	
	public int addLikeByPostIdAndUserId(int postId, int userId) {
		return likeDAO.insertLikeByPostIdAndUserId(postId, userId);
	}
	
	public int getCountLikeByPostId(int postId) {
		return likeDAO.selectCountLikeByPostId(postId);
	}
}
