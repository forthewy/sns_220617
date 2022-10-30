package com.sns.home;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.home.bo.HomeBO;
import com.sns.home.model.HomeView;

@RequestMapping("/home")
@Controller
public class HomeController {

	@Autowired
	private HomeBO homeBO;

	@RequestMapping("/home_view/{homeUserLoginId}")
	public String homeView(@PathVariable String homeUserLoginId
			, HttpSession session
			, Model model) {

		// User user = userBO.getUserByLoginId(homeUserId);
		Integer userId = (Integer) session.getAttribute("userId");
		HomeView homeView = homeBO.generateHomeView(homeUserLoginId, userId);
		
		// model.addAttribute("homeUser", user);
		model.addAttribute("viewName", "home/home");
		model.addAttribute("home", homeView);

		return "template/layout";
	}

}
