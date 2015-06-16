package com.nhncorp.facenote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.bo.PostBO;
import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.Post;
import com.nhncorp.facenote.model.TextTypePost;
import com.nhncorp.facenote.model.User;

@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	
	
	@RequestMapping(value="/writepost")
	@ResponseBody
	public String writePost(@RequestParam(value="user", required=false) String userId) {
		//TODO : writePost 테스트
		List<User> userList = userBO.getUserList();
		Post post = new TextTypePost();
		post.setContent("내용1");
		
		for(User user : userList) {
			if(user.getUserId().equals(userId)) {
				post.setWriter(user);
			}
		}
		
		if(post.getWriter() == null) {
			return "fail";
		}
				
		postBO.writePost(userList.get(0), post);
		
		return "success";
	}
	
	@RequestMapping(value="/viewtimeline")
	public ModelAndView viewTimeline(@RequestParam(value="user", required=false) String userId) {
		ModelAndView mav = new ModelAndView("viewtimeline");
		
		if(userId.isEmpty()) {
			return mav;
		}
		
		List<Post> postList = postBO.getTimelineList(userId);
		mav.addObject("posts", postList);
		
		return mav;
	}
}
