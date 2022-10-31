package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public int addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath  = fileManagerService.saveFile(userLoginId, file);
		
		return postDAO.insertPost(userId, content, imagePath);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public List<Post> getPostListByUserId(int userId) {
		return postDAO.selectPostListByUserId(userId);
	}
	
	public int deletePostByPostId(int postId) {
		return postDAO.deletePostByPostId(postId);
	}
	
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdAndUserId(postId, userId);
	}
	
	//@Transactional rollback // 잘못되는 경우 어떻게 확인해야되지..... 뭐지.....
	@Transactional(rollbackFor = Exception.class)
	public void deletePost(int postId, int userId) {
		// 기존글 가져오기
		Post post = getPostByPostIdAndUserId(postId, userId);
		
		
		//  이미지 삭제
		fileManagerService.deleteFile(post.getImgPath());
		
		// 글 삭제
		deletePostByPostId(postId);
		
		
		
		// 좋아요들 삭제
		likeBO.deleteLikeByPostId(postId);
		
		// 댓글들 삭제
		commentBO.deleteCommentByPostId(postId);
	}
}
