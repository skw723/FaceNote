package com.nhncorp.facenote.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nhncorp.facenote.InterceptorCheck;
import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.UserModel;

@Controller
public class LoginController {
	@Autowired
	UserBO userBO;
	
	private static final Logger logger = Logger.getLogger(PostController.class);
	
	@RequestMapping(value="index")	
	@InterceptorCheck(session=true)
	public ModelAndView index(HttpSession session) {
		return new ModelAndView("main");
	}
	
	@RequestMapping(value="checklogin")
	public RedirectView checkLogin(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("UserModel") UserModel userModel) {
		boolean isLogin = userBO.isLogin(userModel);
		
		if(isLogin) {
			request.getSession().setAttribute("user", userModel.getUser_id());
			return new RedirectView("main.nhn");
		}
		return new RedirectView("login.nhn");
	}
	
	@RequestMapping(value="login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="main")
	@InterceptorCheck(session=true)
	public ModelAndView main() {
		return new ModelAndView("main");
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.nhn";
	}
}
