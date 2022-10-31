package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	/**
	 * 포스트 등록
	 * @param file
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("content") String content,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 550); // 비로그인 상태
			result.put("errorMessage", "포스트 등록에 실패했습니다 로그인해주세요");
			return result;
		}
		
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		int row = postBO.addPost(userId, userLoginId, content, file);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "포스트 등록에 실패했습니다");
		}
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> deletePost(
			@RequestParam("postId") int postId,
			@RequestParam("postUserId") int postUserId,
			HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if (postUserId != userId) {
			result.put("code", 600); // 로그인한 사람과 포스트 작성자가 다르다.
			result.put("errorMessage", "포스트를 작성한 아이디로 로그인하셔야 삭제가능 합니다.");
			return result;
		}
		postBO.deletePost(postId, userId);
		
		result.put("code", 300);
		result.put("result", "success");
		
		return result;
	}
	
}
