package com.sns.like.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LikeDAO likeDAO;
	
	public void likeToggle(int postId, int userId) {
		// 좋아요 있는 지 확인
		int row = getLikeCountByPostIdOrUserId(postId, userId);
		
		int resultRow = 0;
		
		// 있으면 취소 없으면 취소
		if (row > 0) {
			resultRow = deleteLikeByPostIdAndUserId(postId, userId);
			
		} else {
			resultRow = addLikeByPostIdAndUserId(postId, userId);
		}
		
		if (resultRow == 0) {
			log.warn("[likeToggle] 좋아요/좋아요 해제가 실패했습니다. postId:{}, userId:{}", postId, userId);
		}
	}
	
	public int getLikeCountByPostIdOrUserId(int postId, Integer userId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, userId);
	}
	
	public boolean existLike(int postId, int userId) { // boolean exist
		return likeDAO.existLike(postId, userId);
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
