package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@RequestMapping("/timeline_view")
	public String timelineView(HttpSession session, Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		List<CardView> cardViewList = timelineBO.generateCardList();
		
		
		model.addAttribute("viewName","timeline/timeline");
		model.addAttribute("cardViewList", cardViewList);
		
		return "template/layout";
	}
}
