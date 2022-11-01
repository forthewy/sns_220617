package com.sns.user.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.EncryptUtils;
import com.sns.common.FileManagerService;
import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	
	public int existloginId(String loginId) {
		return userDAO.existloginId(loginId);
	}
	
	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}
	
	public User getUserByLoginIdAndPassword(String loginId, String password) {
		return userDAO.getUserByLoginIdAndPassword(loginId, password);
	}
	
	public User getUserByLoginId(String loginId) {
		return userDAO.selectUserByLoginId(loginId);
	}
	
	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}
	
	public int updateUserByLoginId(String loginId,String password,String name,String email,MultipartFile file) {
	
		User user = getUserByLoginId(loginId);
		if (user == null) {
			log.warn("[update user] 회원정보 조회에 실패했습니다. userId:{}", loginId);
			return 0;
		}
		
		if (password != null) {
			password = EncryptUtils.md5(password); // 패스워드가 빈칸이 아닐때 암호화
		}
		
		String profileImgPath = null;
		if (file != null) {
			profileImgPath = fileManagerService.saveFile(user.getLoginId(), file);
			
			if (profileImgPath != null && user.getProfileImgPath() != null) {
				fileManagerService.deleteFile(user.getProfileImgPath());
			}
		}
		
		return userDAO.updateUserByLoginId(loginId, password, name, email, profileImgPath);
	}
}
