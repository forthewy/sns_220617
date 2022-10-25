package com.sns.timeline;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@RequestMapping("/timeline_view")
	public String timelineView(HttpSession session, Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		model.addAttribute("viewName","timeline/timeline");
		
		return "template/layout";
	}
}
