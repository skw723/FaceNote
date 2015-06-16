package com.nhncorp.facenote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.User;

@Controller
public class UserController {
	@Autowired
	private UserBO userBO;
	
	@RequestMapping(value="userlist")
	public ModelAndView getUserList() {
		ModelAndView mav = new ModelAndView("userlist");
		
		List<User> userList = userBO.getUserList();
		mav.addObject("users", userList);
		
		return mav;
	}
}