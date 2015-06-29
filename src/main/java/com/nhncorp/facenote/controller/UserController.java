package com.nhncorp.facenote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.nhncorp.facenote.model.User;
import com.nhncorp.facenote.mybatis.TestModel;

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
	@RequestMapping(value="viewusers")
	public ModelAndView getUserList() {
		ModelAndView mav = new ModelAndView("viewusers");

		List<User> userList = null;
		try {
			userList = userBO.getUserList();
		} catch (IOException e) {
			logger.info("=== getUserList -> IOException ===");
			logger.info(e.getMessage());
			mav.addObject("result", "Fail");
			return mav;
		}

		mav.addObject("result", "Success");
		mav.addObject("users", userList);
		return mav;
	}
	
	@RequestMapping(value="adduserform")
	public ModelAndView addUserForm() {
		ModelAndView mav = new ModelAndView("adduserform");
		
		return mav;
	}

	@RequestMapping(value="adduser")
	@ResponseBody
	public String addUser(@RequestParam(value="user")String userId, HttpServletResponse response) {
		if(StringUtils.isEmpty(userId)) {
			logger.info("=== addUser -> userId is Empty ===");
			return "userId is Empty";
		}
		
		boolean isSuccess = false;
		try {
			isSuccess = userBO.addUser(userId);
		} catch (IOException e) {
			logger.info("=== addUser -> IOException ===");
			logger.info(e.getMessage());
			return "Fail";
		}

		if(isSuccess) {
			return "Success";
		} else {
			logger.info("=== addUser -> Already exist userId ===");
			return "Already exist userId";
		}
	}
	
	@RequestMapping(value="addfriendform")
	public ModelAndView addFriendForm() {
		ModelAndView mav = new ModelAndView("addfriendform");
		
		return mav;
	}
	
	@RequestMapping(value="addfriend")
	@ResponseBody
	public String addFriend(
			@RequestParam(value="user")String userId,
			@RequestParam(value="friend")String friendId) {
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(friendId)) {
			logger.info("=== addFriend -> One or more parameter is empty ===");
			return "One or more parameter is empty";
		}
		
		if(StringUtils.equals(userId, friendId)) {
			logger.info("=== addFriend -> Two parameter is equivalent ===");
			return "Two parameter is equivalent";
		}
		
		boolean isSuccess = false;
		
		try {
			isSuccess = userBO.addFriend(userId, friendId);
		} catch (IOException e) {
			logger.info("=== addFriend -> IOException ===");
			logger.info(e.getMessage());
			return "Fail";
		}
		
		if(!isSuccess) {
			logger.info("=== addFriend -> Could not find user ===");
			return "Could not find user";
		}
		
		return "Success";
	}
	
	@RequestMapping(value="mybatis")
	@ResponseBody
	public String my() {
		try {
			//SqlSession s = session.getObject().openSession();
			List<TestModel> list = session.selectList("TEST.get");
			return list.get(0).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
}