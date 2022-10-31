package com.sns.follow.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;

@Service
public class FollowBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FollowDAO followDAO;
	
	// 팔로우 토글
	public void followToggle(int followerUserId, int followedUserId) {
		int row = getFollowCountByFollowerUserIdOrFollowedUserId(followerUserId, followedUserId);
		
		if (row > 0) {
			int resultRow = deleteFollow(followerUserId, followedUserId);
			if (resultRow == 0) {
				log.debug("[팔로우 취소 실패] 팔로우 취소에 실패하였습니다 followerUserId:{}, followedUserId:{}"
						, followerUserId, followedUserId);
			}
			return;
		}
		
		int resultRow = addFollow(followerUserId, followedUserId);
		if (resultRow == 0) {
			log.debug("[팔로우 실패] 팔로우에 실패하였습니다 followerUserId:{}, followedUserId:{}"
					, followerUserId, followedUserId);
		}
		return;
	}
	
	
	// 토글로 변경
	public int addFollow(int followerUserId, int followedUserId) {
		return followDAO.insertFollow(followerUserId, followedUserId);
	}
	
	public int deleteFollow(int followerUserId, int followedUserId) {
		return followDAO.deleteFollow(followerUserId, followedUserId);
	}
	
	// 팔로워 혹은 팔로잉 수 확인
	public int getFollowCountByFollowerUserIdOrFollowedUserId(Integer followerUserId,
			Integer followedUserId) {
		return followDAO.selectFollowCountByFollowerUserIdOrFollowedUserId(followerUserId, followedUserId);
	}
	
	public List<Integer> getFollowUserIdListByFollowedUserId(int followedUserId){
		return followDAO.selectFollowUserIdListByFollowedUserId(followedUserId);
	}
	
	public List<Integer> getFolloweeUserIdListByFollowerUserId(int followerUserId){
		return followDAO.selectFolloweeUserIdListByFollowerUserId(followerUserId);
	}
	
}
