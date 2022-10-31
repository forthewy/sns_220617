package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	public int addComment(int userId, int postId, String comment) { 
		return commentDAO.insertComment(userId, postId, comment);
	}
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	
	
	// input : 글 번호
	// output : 가공된 댓글 객체 화면용
	public List<CommentView> generateCommentViewByPostId(int postId) {
		// 결과물
		List<CommentView> commentListViewList = new ArrayList<>();
		
		// 댓글 목록을 가져온다
		List<Comment> commentList = getCommentListByPostId(postId);
		
		// 반복문 순회 => CommentView => List<CommentView> 에 채운다.
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 코멘트 넣기
			commentView.setComment(comment);
			
			// 유저 넣기 userBO 에서 commentBO 부르면 절대로 안됨
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
			
			// 리스트에 담는다.
			commentListViewList.add(commentView);
		}
		
		// 리스트를 리턴한다.
		return commentListViewList;
	}
	
	public int deleteCommentByPostId(int postId) {
		return commentDAO.deleteCommentByPostId(postId);
	}
	
}
