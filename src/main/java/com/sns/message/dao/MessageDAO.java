package com.sns.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.message.model.Message;

@Repository
public interface MessageDAO {

	// 메세지 보내기
	public int insertMessage(
			@Param("chatroomId") int chatroomId,
			@Param("userIdSender") int userIdSender,
			@Param("userIdReceiver") int userIdReceiver, 
			@Param("content")String content);
	
	
	// 1명과 메세지 목록
	public List<Message> selectMessageListByChatroomId(int chatroomId);
			
	
	// 1명(user)과 메세지를 주고 받은 사람들id
	public List<Integer> selectMessageUserIdListByUserId(int userId);
}
	
