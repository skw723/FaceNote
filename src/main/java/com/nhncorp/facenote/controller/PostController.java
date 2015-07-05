package com.nhncorp.facenote.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.InterceptorCheck;
import com.nhncorp.facenote.bo.PostBO;
import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.PostModel;
import com.nhncorp.facenote.model.PostModelValidator;

@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	
	private static final Logger logger = Logger.getLogger(PostController.class);
	
	@RequestMapping(value="post")
	@InterceptorCheck(session=true)
	public ModelAndView post(HttpSession session) {
		ModelAndView mav = new ModelAndView("post");
		String user_id = (String) session.getAttribute("user");
		
		List<PostModel> posts = postBO.getPostList(user_id);
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="addpost")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String addFriend(@ModelAttribute PostModel postModel, HttpSession session, MultipartFile image, BindingResult bindingResult) {
		new PostModelValidator().validate(postModel, bindingResult);
		if(bindingResult.hasErrors()) {
			return "fail";
		}
		
		String user = (String) session.getAttribute("user");
		postModel.setUser_id(user);
		
		boolean isSuccess = postBO.addPost(postModel, image);
		
		if(isSuccess == true) {
			return "success";
		}
		
		return "fail";
	}
	
	@RequestMapping(value="modifypost")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String modifyPost(@ModelAttribute PostModel postModel, BindingResult bindingResult) {
		new PostModelValidator().validate(postModel, bindingResult);
		if(bindingResult.hasErrors()) {
			return "fail";
		}
		
		boolean isSuccess = postBO.modifyPost(postModel);
		
		if(isSuccess) { 
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping(value="deletepost")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String deletePost(@RequestParam long post_no) {
		boolean isSuccess = postBO.deletePost(post_no);
		
		if(isSuccess) {
			return "success";
		} 
		return "fail";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String atchFileSzException(Exception e) {
		return e.toString();
	}
}