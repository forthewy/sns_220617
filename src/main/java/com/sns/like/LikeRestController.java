package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// /like?postId=111 도 가능하지만....
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		

		if (userId == null) {
			result.put("code", 550);
			result.put("errorMessage", "좋아요 혹은 좋아요 취소에 실패했습니다. 로그인해주세요");
			
			return result;
		} // 비로그인
		
		// like를 가지고 와서 있으면 지워버리고 없으면 토글 
		likeBO.likeToggle(postId, userId);
		
		result.put("code", 300);
		result.put("result", "성공");
		return result;
		
		// 컨트롤러는 로직이 없어야 해서 수정필요했다...
	}
}
