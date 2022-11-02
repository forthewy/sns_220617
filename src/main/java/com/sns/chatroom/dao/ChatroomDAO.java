package com.sns.chatroom.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.chatroom.model.Chatroom;

@Repository
public interface ChatroomDAO {
	
	public Integer selectChatroomId(
			@Param("senderId") int senderId,
			@Param("receiverId") int receiverId);
	
	public List<Integer> selectChatroomIdListByUserId(int userId);
	
	public Chatroom selectChatroomByChatroomId(int chatroomId);
	
	public int insertChatroom(
			@Param("senderId") int senderId,
			@Param("receiverId") int receiverId);
	
}
