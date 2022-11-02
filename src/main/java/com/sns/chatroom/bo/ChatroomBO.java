package com.sns.chatroom.bo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.chatroom.dao.ChatroomDAO;
import com.sns.chatroom.model.Chatroom;
import com.sns.chatroom.model.ChatroomView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ChatroomBO {

	// chatroom에서 messageBO 못씀!!
	@Autowired
	private ChatroomDAO chatroomDAO;
	
	@Autowired
	private UserBO userBO;	
	
	public int getChatroomId(int senderId, int receiverId) {
		Integer chatroomId = chatroomDAO.selectChatroomId(senderId, receiverId);
		if (chatroomId == null) {
			// 없으면 넣는다. 이때 chatroom DB를 senderId와 receiverId를 유니크 인덱스로 만들어 중복되지 않게 한다. 
			addChatroom(senderId, receiverId);
			chatroomId = chatroomDAO.selectChatroomId(senderId, receiverId);
		}
		return chatroomId;
	}
	
	public Chatroom getChatroomByChatroomId(int chatroomId) {
		return chatroomDAO.selectChatroomByChatroomId(chatroomId);
	}
	
	public List<ChatroomView> getChatroomListByUserId(int userId) {
		List<ChatroomView> chatroomViewList = new LinkedList<>();
		
		// 유저가 참여하는 채팅방 아이디를 뽑아서
		List<Integer> chatroomIdList = chatroomDAO.selectChatroomIdListByUserId(userId);
		
		// 각 채팅 방에서 유저가 아닌 사람의 프로필 이미지와 로그인 아이디를 넣는다.
		for(Integer chatroomId: chatroomIdList) {
			ChatroomView chatroomView = new ChatroomView();
			Chatroom chatroom = getChatroomByChatroomId(chatroomId);
			if (chatroom.getSenderId() != userId) {
				User user = userBO.getUserById(userId);
				chatroomView.setLoginId(user.getLoginId());
				chatroomView.setProfileImgPath(user.getProfileImgPath());
			};
		}
		
		return ;
	}
	
	public int addChatroom(int senderId,int receiverId) {
		return chatroomDAO.insertChatroom(senderId, receiverId);
	}
	
}
