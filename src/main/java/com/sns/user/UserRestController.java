package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 아이디 중복확인
	 * @param loginId
	 * @return
	 */
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
	
	/**
	 * 회원가입
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
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
	
	/**
	 * 로그인
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
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
		
		session.setAttribute("profileImg", user.getProfileImgPath());
		session.setAttribute("userName", user.getName());
		session.setAttribute("userLoginId", user.getLoginId());
		session.setAttribute("userPassword", user.getPassword());
		session.setAttribute("userId", user.getId());
		session.setAttribute("userEmail", user.getEmail());
	} else {
		result.put("code", 500);
		result.put("errorMessage", "아이디 혹은 비밀번호가 틀립니다");
	}
	
	return result;
	}
	
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="profileImg", required=false) MultipartFile file,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="email", required=false) String email,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
}
