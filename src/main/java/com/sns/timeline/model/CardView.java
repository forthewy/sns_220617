package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class CardView {
	// 글 1개
	private Post post;
	
	// 댓글 N개
	private List<CommentView> commentList;

	// 글쓴이 정보	
	private User user;
	
	// 좋아요 개수
	
	// 로그인 사람이 좋아요를 눌렀는지.
	private boolean filledLike; // 눌렀으면 true 아니면 false
 	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	
	
}
