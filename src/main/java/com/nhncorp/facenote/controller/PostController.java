package com.nhncorp.facenote.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.bo.PostBO;
import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.Post;

@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	
	private static final Logger logger = Logger.getLogger(PostController.class);
	
	@RequestMapping(value="index")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	
	@RequestMapping(value="writepostform")
	public ModelAndView writePostForm() {
		ModelAndView mav = new ModelAndView("writepostform");
		
		return mav;
	}
	
	
	@RequestMapping(value="/writepost", method=RequestMethod.POST)
	@ResponseBody
	public String writePost(@RequestParam(value="user") String userId,
			@RequestParam(value="content", defaultValue="")String content,
			@RequestParam(value="image")MultipartFile file) {
		if(StringUtils.isEmpty(userId)) {
			logger.info("=== writePost -> userId is empty ===");
			return "userId is empty";
		}
		
		boolean isSuccess = false;
		try {
			isSuccess = postBO.writePost(userId, content, file);
		} catch (IOException e) {
			logger.info("=== writePost -> IOException ===");
			logger.info(e.getMessage());
			return "Fail";
		}
			
		if(isSuccess) {
			return "Success";
		} else {
			logger.info("=== writePost -> could not find userId ===");
			return "could not find userId";
		}
	}
	
	@RequestMapping(value="/viewpost")
	public ModelAndView viewPost(@RequestParam(value="user") String userId) {
		ModelAndView mav = new ModelAndView("viewpost");
		
		if(StringUtils.isEmpty(userId)) {
			logger.info("=== viewPost -> userId is empty ===");
			mav.addObject("result", "Fail");
			return mav;
		}
		
		List<Post> postList = new ArrayList<Post>();
		
		try {
			postList = postBO.getPostList(userId);
		} catch (IOException e) {
			logger.info("=== viewPost -> IOException ===");
			logger.info(e.getMessage());
			mav.addObject("result", "Fail");
			return mav;
		}
		
		if(postList == null) {
			mav.addObject("result", "Fail");
			return mav;
		}
		
		mav.addObject("result", "Success");
		mav.addObject("user", userId);
		mav.addObject("posts", postList);
		
		return mav;
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
}