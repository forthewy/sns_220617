package com.sns.follow.bo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;
import com.sns.follow.model.FollowUser;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class FollowBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FollowDAO followDAO;
	
	@Autowired
	private UserBO userBO;
	
	// 팔로우 토글
	public void followToggle(int followerUserId, int followeeUserId) {
		int row = getFollowCountByFollowerUserIdOrFolloweeUserId(followerUserId, followeeUserId);
		
		if (row > 0) {
			int resultRow = deleteFollow(followerUserId, followeeUserId);
			if (resultRow == 0) {
				log.debug("[팔로우 취소 실패] 팔로우 취소에 실패하였습니다 followerUserId:{}, followeeUserId:{}"
						, followerUserId, followeeUserId);
			}
			return;
		}
		
		int resultRow = addFollow(followerUserId, followeeUserId);
		if (resultRow == 0) {
			log.debug("[팔로우 실패] 팔로우에 실패하였습니다 followerUserId:{}, followeeUserId:{}"
					, followerUserId, followeeUserId);
		}
		return;
	}
	
	
	// 토글로 변경
	public int addFollow(int followerUserId, int followeeUserId) {
		return followDAO.insertFollow(followerUserId, followeeUserId);
	}
	
	public int deleteFollow(int followerUserId, int followeeUserId) {
		return followDAO.deleteFollow(followerUserId, followeeUserId);
	}
	
	// 팔로워 혹은 팔로잉 수 확인
	public int getFollowCountByFollowerUserIdOrFolloweeUserId(Integer followerUserId,
			Integer followeeUserId) {
		return followDAO.selectFollowCountByFollowerUserIdOrFolloweeUserId(followerUserId, followeeUserId);
	}
	
	// 팔로이id 조회
	public List<Integer> getFolloweeIdByFollowUserId(Integer followerUserId) {
		return followDAO.selectFolloweeUserIdByFollowerUserId(followerUserId);
	}
	
	// 팔로워id 조회
	public List<Integer> getFollowerIdByFollowUserId(Integer followeeUserId) {
		return followDAO.selectFollowerUserIdByFolloweeUserId(followeeUserId);
	}
	
	
	// 팔로워 혹은 팔로이 조회 null 값 아닌 쪽으로 id 목록 만들기
	public List<FollowUser> getFollowUserByUserId(Integer followerId, Integer followeeUserId) {
		
		List<Integer> followUserIdList = new ArrayList<>();
		
		if (followerId == null) {
			followUserIdList = getFolloweeIdByFollowUserId(followeeUserId);
		} else {
			followUserIdList = getFollowerIdByFollowUserId(followerId);
		}
		
		List<FollowUser> followUserList = new LinkedList<>();
		
		for (Integer followUserId: followUserIdList) {
			// 아이디로 유저를 구하고
			User user = userBO.getUserById(followUserId);
			
			// 유저 정보로 정보를 넣는다.
			FollowUser followUser = new FollowUser();
			followUser.setLoginId(user.getLoginId());
			followUser.setProfileImgPath(user.getProfileImgPath());
			
			followUserList.add(followUser);
		}
			
		return followUserList;
		
	}
	
	
}
