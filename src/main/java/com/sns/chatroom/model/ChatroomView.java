package com.sns.chatroom.model;

public class ChatroomView {
	// 채팅방에 참여중인 목록들을 보여주기 위한 뷰 모델
	private String loginId;
	
	private String profileImgPath;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getProfileImgPath() {
		return profileImgPath;
	}

	public void setProfileImgPath(String profileImgPath) {
		this.profileImgPath = profileImgPath;
	}
}
	