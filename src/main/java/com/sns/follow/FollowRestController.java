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
	
	@RequestMapping("/create")
	public Map<String, Object> createFollow(
			@RequestParam("followedUserId") int followedUserId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		int followUserId = (int) session.getAttribute("userId");
		int row = followBO.addFollow(followUserId, followedUserId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "팔로우에 실패했습니다");
		}
		
		return result;
	}
}
