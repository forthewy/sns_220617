package com.sns.home.bo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.bo.FollowBO;
import com.sns.home.model.HomeView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class HomeBO {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private FollowBO followBO;
	
	// HomeView 는 여러 DB가 함께 나와야하므로 HomeView 를 구성하는 BO를 만든다.
	public HomeView generateHomeView(
			String homeUserLoginId,
			Integer userId){
		HomeView homeView = new HomeView();
		
		// 홈 주인 유저
		User homeUser = userBO.getUserByLoginId(homeUserLoginId);
		homeView.setUser(homeUser);
		
		// 포스트 리스트
		List<Post> postList = postBO.getPostListByUserId(homeUser.getId());
		homeView.setPostList(postList);
		
		//팔로잉 수
		int following = followBO.getFollowCountByFollowerUserIdOrFollowedUserId(homeUser.getId(), null);
		homeView.setFollowingCount(following);
				
		//팔로워 수 
		int follower = followBO.getFollowCountByFollowerUserIdOrFollowedUserId(null , homeUser.getId());
		homeView.setFollowerCount(follower);
		
		//내가 팔로우 했는지.
		int follow = followBO.getFollowCountByFollowerUserIdOrFollowedUserId(homeUser.getId(), userId);
		if (follow > 0) {
			homeView.setFollowOrNot(true);
		} else if (follow == 0){
			homeView.setFollowOrNot(false);
		}
		
		return homeView;
	}
}
