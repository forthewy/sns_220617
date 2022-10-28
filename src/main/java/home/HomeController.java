package home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/home")
@Controller
public class HomeController {

	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/home_view/{homeUserId}")	
	public String homeView(
			@PathVariable String homeUserId,
			Model model) {
		
		User user = userBO.getUserByLoginId(homeUserId);
		
		model.addAttribute("homeUser", user);
		model.addAttribute("viewName", "home/home");
		
		return "template/layout";
	}
	
}
