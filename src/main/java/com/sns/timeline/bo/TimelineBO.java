package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;

@Service
public class TimelineBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	public List<CardView> generateCardList() {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다
		List<Post> postList = postDAO.selectPostList();
		
		// 반복문 -> cardView 에 채워넣는다.
		for (Post post : postList) {
			
			List<Comment> commentList = commentDAO.selectCommentByPostId(post.getId());
			
			CardView cardView = new CardView();
			cardView.setCommentList(commentList);
			cardView.setPost(post);
			cardViewList.add(cardView);
		}
		
		return cardViewList;
	}
}
