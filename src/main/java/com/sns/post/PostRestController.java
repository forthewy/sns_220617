package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("content") String content,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
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
	
}
