package com.nhncorp.facenote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.bo.PostBO;
import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.PostModel;

@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	
	private static final Logger logger = Logger.getLogger(PostController.class);
	
	@RequestMapping(value="writepostform")
	public ModelAndView writePostForm() {
		ModelAndView mav = new ModelAndView("writepostform");
		
		return mav;
	}
	
	@RequestMapping(value="post")
	public ModelAndView post(HttpSession session) {
		ModelAndView mav = new ModelAndView("post");
		String user_id = (String) session.getAttribute("user");
		
		List<PostModel> posts = postBO.getPostList(user_id);
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="addpost")
	@ResponseBody
	public String addFriend(@ModelAttribute PostModel postModel, HttpSession session, MultipartFile image) {
		String user = (String) session.getAttribute("user");
		postModel.setUser_id(user);
		
		boolean isSuccess = postBO.addPost(postModel, image);
		
		if(isSuccess == true) {
			return "success";
		}
		
		return "fail";
	}
	
	@RequestMapping(value="/viewpost")
	public ModelAndView viewPost(@RequestParam(value="user") String userId) {
		return null;
	}
	
	@RequestMapping(value="/*.jpg")
	public @ResponseBody byte[] getImage(HttpServletRequest request) {
		String fileName = request.getRequestURI();
		try {
			return postBO.getImageFileByte(fileName);
		} catch (IOException e) {
			logger.info("=== ImageFilei load fail ===");
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value="modifypost")
	@ResponseBody
	public String modifyPost(@ModelAttribute PostModel postModel) {
		boolean isSuccess = postBO.modifyPost(postModel);
		
		if(isSuccess) { 
			return "success";
		}
		return "fail";
	}
}