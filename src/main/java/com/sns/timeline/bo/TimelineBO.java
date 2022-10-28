package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// jsp -> timelineController -> timelineBO -> postBO
	//											-> commentBO
	public List<CardView> generateCardList(Integer userId) { // 로그인이 안된 사람도 카드 목록이 보여야 하기 때문에 Integer userId
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다
		List<Post> postList = postBO.getPostList();
		
		// 반복문 -> cardView 에 채워넣는다.
		for (Post post : postList) {
			CardView cardView = new CardView();
			// 글정보
			cardView.setPost(post);
			// 글쓴이 정보
			User user = userBO.getUserById(post.getUserId());
			cardView.setUser(user);
			
			// 글하나에 해당하는 댓글 목록
			List<CommentView> commentList = commentBO.generateCommentViewByPostId(post.getId());
			cardView.setCommentList(commentList);
			
			// 내가 좋아요를 눌렀는지
			
			if (userId == null) {
				cardView.setFilledLike(false); // userId가 null이면 결과가 0이다. 별도의 예외처리가 필요하지 않다.
			} else {
				int count = likeBO.getLikeCountByPostIdOrUserId(post.getId(), userId); // 채워져있는지 확인
				cardView.setFilledLike(count > 0 ? true: false);
			}
			//좋아요 갯수
			cardView.setLikeCount(likeBO.getLikeCountByPostIdOrUserId(post.getId(), null));
			
			// 카드 리스트에 채우기 !!!!
			cardViewList.add(cardView);
			
		}
			return cardViewList;
	}
}
