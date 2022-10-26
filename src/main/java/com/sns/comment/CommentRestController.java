package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> comment(
			@RequestParam("comment") String comment,
			@RequestParam("postId") int postId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		 
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 550);
			result.put("errorMessage", "댓글 쓰기에 실패했습니다. 로그인해주세요");
			
			return result;
		}
		
		int row =  commentBO.addComment(userId, postId, comment);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글 쓰기에 실패했습니다");
		}
		 
		return result;
	}
}
