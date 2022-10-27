package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.Comment;
import com.sns.post.model.Post;

public class CardView {
	// 글 
	private Post post;
	
	// 댓글 N개
	private List<Comment> commentList;

	
	// 좋아요
	
	// 로그인 사람이 좋아요를 눌렀는지.
		
		
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}