package com.sns.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 로그인
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_in_view") 
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	/**
	 * 회원가입
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_up_view") 
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	
	@RequestMapping("/sign_out") 
	public String signOut(HttpSession session) {
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userPassword");
		session.removeAttribute("profileImg");
		session.removeAttribute("userId");
		session.removeAttribute("userEmail");
		
		return "redirect:/user/sign_in_view";
	}

	@RequestMapping("/home_view")
	public String homeView(
			@RequestParam("homeUserId") String homeUserId,
			Model model) {
		
		User user = userBO.getUserByLoginId(homeUserId);
		
		model.addAttribute("homeUser", user);
		model.addAttribute("viewName", "user/home");
		
		return "template/layout";
	}
	
	/**
	 * 회원정보 수정화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/info_view")
	public String updateView(HttpSession session, Model model) {
		
		String loginId = (String) session.getAttribute("userLoginId");
		
		if (loginId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		model.addAttribute("viewName", "user/info");
		
		return "template/layout";
	}
}
