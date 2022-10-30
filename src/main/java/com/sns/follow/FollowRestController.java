package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

@RequestMapping("/follow")
@RestController
public class FollowRestController {
	
	@Autowired
	private FollowBO followBO;
	
	@RequestMapping("/follow")
	public Map<String, Object> followToggle(
			@RequestParam("homeUserId") int homeUserId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 550);
			result.put("errorMessage", "좋아요 혹은 좋아요 취소에 실패했습니다. 로그인해주세요");
			
			return result;
		}
		
		followBO.followToggle(homeUserId, userId);
		
		result.put("code", 300);
		result.put("result", "성공");
		
		return result;
	}
}
