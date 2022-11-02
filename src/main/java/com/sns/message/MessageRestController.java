package com.sns.message;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.message.bo.MessageBO;

@RequestMapping("/message")
@RestController
public class MessageRestController {
	
	@Autowired
	private MessageBO messageBO;
	
	/**
	 * 메세지 전송
	 * @param content
	 * @param receiverId
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> messageCreate(
			@RequestParam("content") String content,
			@RequestParam("receiverId") int receiverId,
			HttpSession session) {
		
		Integer senderId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if (senderId == null) {
			result.put("code", 550);
			result.put("errorMessage", "메세지 전송에 실패했습니다. 로그인해주세요");
		}
		
		// 보낼때 확인해보고 추가하는게 자연스러울것 같다. 미리 만들수는 없으니...
		int row = messageBO.addMessage(senderId, receiverId, content);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "메세지 전송에 실패했습니다");
		}
		
		return result;
	}
	
	
}
