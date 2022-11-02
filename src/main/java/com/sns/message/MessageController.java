package com.sns.message;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.chatroom.bo.ChatroomBO;
import com.sns.message.bo.MessageBO;
import com.sns.message.model.Message;
import com.sns.user.model.User;

@RequestMapping("/message")
@Controller
public class MessageController {
	
	@Autowired
	private MessageBO messageBO;
	
	// 주고 받은 메세지 내역 확인 / 메세지를 보내는 화면
	@RequestMapping("/message_view")
	public String messageView(
			@RequestParam("receiverId") int receiverId,
			HttpSession session,
			Model model) {
		
		
		Integer senderId = (Integer) session.getAttribute("userId"); 
		
		if (senderId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		List<Message> messageList = messageBO.getMessageListByChatroomId(senderId, receiverId);
				
		model.addAttribute("messageList", messageList);
		
		// 메세지 보내기버튼, 메세지 카드 구분용
		model.addAttribute("receiverId", receiverId);
		model.addAttribute("viewName", "/message/message");
		
		return "template/layout";
	}
	
	// 주고받은 메세지화면으로 가는 링크리스트를 보여주는 화면
	@RequestMapping("/messageList_view")
	public String messageListView(
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId"); 
		List<ChatroomView> chatroomList = 
				
		model.addAttribute("userList", userList);
		model.addAttribute("viewName", "/message/messageList");
		
		return "template/layout";
	}
}
