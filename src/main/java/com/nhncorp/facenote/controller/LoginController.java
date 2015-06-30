package com.nhncorp.facenote.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.UserModel;

@Controller
public class LoginController {
	@Autowired
	UserBO userBO;
	
	private static final Logger logger = Logger.getLogger(PostController.class);
	
	@RequestMapping(value="login")
	public ModelAndView index(HttpSession session) {
		boolean isLogin = userBO.isLoginState(session);
		ModelAndView mav = new ModelAndView();
		
		if(isLogin) {
			mav.setViewName("redirect:main.nhn");
		} else {
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="checklogin")
	public void login(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("UserModel") UserModel userModel) throws IOException {
		boolean isLogin = userBO.isLogin(userModel);
		
		if(isLogin) {
			request.getSession().setAttribute("user", userModel.getUser_id());
			response.sendRedirect("main.nhn");
			return ;
		}
		response.sendRedirect("index.nhn");
	}
	
	@RequestMapping(value="main")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("main");
		
		return mav;
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:login.nhn";
	}
}
