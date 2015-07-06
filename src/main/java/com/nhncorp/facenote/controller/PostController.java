package com.nhncorp.facenote.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addpost")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String addpost(@ModelAttribute PostModel postModel, HttpSession session, MultipartFile image) throws Exception {
		JSONObject result = new JSONObject();
		
		String user = (String) session.getAttribute("user");
		postModel.setUser_id(user);
		
		boolean isSuccess = postBO.addPost(postModel, image);
		
		if(isSuccess == true) {
			result.put("result", "success");
			result.put("msg", URLEncoder.encode("포스트가 등록되었습니다", "UTF-8"));
			return result.toString();
		}
		
		result.put("result", "fail");
		result.put("msg", URLEncoder.encode("포스트 등록에 실패했습니다.", "UTF-8"));
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="modifypost")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String modifyPost(@ModelAttribute PostModel postModel) throws Exception {
		JSONObject result = new JSONObject();
		
		boolean isSuccess = postBO.modifyPost(postModel);
		
		if(isSuccess) { 
			result.put("result", "success");
			result.put("msg", URLEncoder.encode("포스트 수정에 성공했습니다.", "UTF-8"));
			return result.toString();
		}
		
		result.put("result", "fail");
		result.put("msg", URLEncoder.encode("포스트 수정에 실패했습니다.", "UTF-8"));
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="deletepost")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String deletePost(@RequestParam long post_no) throws Exception {
		boolean isSuccess = postBO.deletePost(post_no);
		
		JSONObject result = new JSONObject();
		
		if(isSuccess) {
			result.put("result", "success");
			result.put("msg", URLEncoder.encode("포스트 삭제에 성공했습니다.", "UTF-8"));
			return result.toString();
		} 
		
		result.put("result", "fail");
		result.put("msg", URLEncoder.encode("포스트 삭제에 실패했습니다.", "UTF-8"));
		return result.toString();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView atchFileSzException(Exception e) {
		logger.error(e.toString());
		ModelAndView mav = new ModelAndView("error/error");
		return mav;
	}
}