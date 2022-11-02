package com.sns.message.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.chatroom.bo.ChatroomBO;
import com.sns.message.dao.MessageDAO;
import com.sns.message.model.Message;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class MessageBO {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ChatroomBO chatroomBO;
	
	// 메세지 보내기. 채팅방 조회 후 맞는 곳에 보내준다.
	public int addMessage(int userIdSender, int userIdReceiver, String content) {
		int chatroomId = chatroomBO.getChatroomId(userIdSender, userIdReceiver);
		
		return messageDAO.insertMessage(chatroomId, userIdSender, userIdReceiver, content);
	}
	
	// 1명과의 메세지 리스트 채팅방 번호로 조회하는 방식으로 수정
	public List<Message> getMessageListByChatroomId(int userIdSender, int userIdReceiver){
		int chatroomId = chatroomBO.getChatroomId(userIdSender, userIdReceiver);
		
		return messageDAO.selectMessageListByChatroomId(chatroomId);
	}
	
	// 한 유저가 메세지를 주고 받은 사람들Id 리스트
	public List<Integer> getMessageUserIdListByUserId(int userId) {
		return messageDAO.selectMessageUserIdListByUserId(userId);
	}
	
	public List<User> getUserByMessageUserId(int userId) {
		
		List<User> userList = new ArrayList<>();
		
		// 메세지를 주고 받은 사람들의 id
		List<Integer> messageUserIdList = getMessageUserIdListByUserId(userId);
		
		// userBO로 user를 받아 UserList에 넣는다.
		for (Integer messageUserId : messageUserIdList) {
			User user = new User();
			user = userBO.getUserById(messageUserId);
			userList.add(user);
		}
		
		return userList;
	}
	
	
}
