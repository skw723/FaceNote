package com.nhncorp.facenote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

@Controller
public class UserController {
	@Autowired
	private UserBO userBO;
	@Autowired
	SqlSessionTemplate session;

	private static final Logger logger = Logger.getLogger(UserController.class);

	/**모든 사용자 조회(아이디, 친구목록)
	 * @return
	 */
	@RequestMapping(value="userinfo")
	public ModelAndView getUserList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("userinfo");
		String user_id = (String) request.getSession().getAttribute("user");
		
		UserModel userModel = userBO.getUserInfo(user_id);
		mav.addObject("user", userModel);
		return mav;
	}
	
	@RequestMapping(value="adduserform")
	public ModelAndView addUserForm() {
		ModelAndView mav = new ModelAndView("adduserform");
		
		return mav;
	}

//	@RequestMapping(value="adduser")
//	@ResponseBody
//	public String addUser(@RequestParam(value="user")String userId, HttpServletResponse response) {
//		if(StringUtils.isEmpty(userId)) {
//			logger.info("=== addUser -> userId is Empty ===");
//			return "userId is Empty";
//		}
//		
//		boolean isSuccess = false;
//		try {
//			isSuccess = userBO.addUser(userId);
//		} catch (IOException e) {
//			logger.info("=== addUser -> IOException ===");
//			logger.info(e.getMessage());
//			return "Fail";
//		}
//
//		if(isSuccess) {
//			return "Success";
//		} else {
//			logger.info("=== addUser -> Already exist userId ===");
//			return "Already exist userId";
//		}
//	}
	
	@RequestMapping(value="friend")
	public ModelAndView addFriendForm(HttpSession session) {
		ModelAndView mav = new ModelAndView("friend");
		String user_id = (String) session.getAttribute("user");
		
		List<FriendModel> frndList = userBO.getFriendList(user_id);
		mav.addObject("friends", frndList);
		
		List<FriendModel> notAccpList = userBO.getNotAccpList(user_id);
		mav.addObject("notaccps", notAccpList);
		
		return mav;
	}
	
	@RequestMapping(value="addfriend")
	@ResponseBody
	public String addFriend(@RequestParam String frnd_id, HttpSession session) {
		String user = (String) session.getAttribute("user");
		
		if(StringUtils.equals(user, frnd_id)) {
			return "fail";
		}
		
		FriendModel frndModel = new FriendModel();
		frndModel.setUser_id(user);
		frndModel.setFrnd_id(frnd_id);
		
		boolean isSuccess = userBO.addFriend(frndModel);
		
		if(isSuccess == true) {
			return "success";
		}
		
		return "fail";
	}
	
	@RequestMapping("accpfrnd")
	@ResponseBody
	public String accpFrnd(@RequestParam String frnd_id, HttpSession session) {
		String user = (String) session.getAttribute("user");
		FriendModel frndModel = new FriendModel();
		frndModel.setUser_id(frnd_id);
		frndModel.setFrnd_id(user);
		
		boolean isSuccess = userBO.accpFrnd(frndModel);
		if(isSuccess) { 
			return "success";
		}
		return "fail";
	}
}