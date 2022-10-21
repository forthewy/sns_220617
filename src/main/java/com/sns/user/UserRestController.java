package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicate(String loginId) {
		Map<String, Object> result = new HashMap<>();
		
		int row = userBO.existloginId(loginId);
		
		if (row > 0) {
			result.put("code", 100);
			result.put("duplicate", true);
		} else {
			result.put("code", 100);
			result.put("duplicate", false);
		}
		
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		userBO.addUser(loginId, encryptPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", "success");
		result.put("code", 200);
		
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session){
	
	String encryptPassword = EncryptUtils.md5(password);
	
	User user = userBO.getUserByLoginIdAndPassword(loginId, encryptPassword);
	
	Map<String, Object> result = new HashMap<>();
	
	if (user != null) {
		result.put("code", 300);
		result.put("result", "success");
		
		
		session.setAttribute("userName", user.getName());
		session.setAttribute("userLoginId", user.getLoginId());
		session.setAttribute("userPassword", user.getPassword());
	} else {
		result.put("code", 500);
		result.put("errorMessage", "아이디 혹은 비밀번호가 틀립니다");
	}
	
	return result;
	}
}
