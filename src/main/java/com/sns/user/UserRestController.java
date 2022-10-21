package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.bo.UserBO;

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

}
