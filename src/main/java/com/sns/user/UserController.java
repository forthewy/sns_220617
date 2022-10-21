package com.sns.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@RequestMapping("/sign_in_view") // 로그인
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userPassword");
		
		return "redirect:/user/sign_in_view";
	}

}
