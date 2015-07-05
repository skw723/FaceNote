package com.nhncorp.facenote.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.facenote.InterceptorCheck;
import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

@Controller
public class UserController {
	@Autowired
	private UserBO userBO;
	@Autowired
	SqlSessionTemplate session;
	@Autowired
	MessageSource message;

	private static final Logger logger = Logger.getLogger(UserController.class);

	/**모든 사용자 조회(아이디, 친구목록)
	 * @return
	 */
	@RequestMapping(value="userinfo")
	@InterceptorCheck(session=true)
	public ModelAndView getUserList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("userinfo");
		String user_id = (String) request.getSession().getAttribute("user");
		
		UserModel userModel = userBO.getUserInfo(user_id);
		mav.addObject("user", userModel);
		return mav;
	}
	
	@RequestMapping(value="friend")
	@InterceptorCheck(session=true)
	public ModelAndView addFriendForm(HttpSession session) {
		ModelAndView mav = new ModelAndView("friend");
		String user_id = (String) session.getAttribute("user");
		
		List<FriendModel> frndList = userBO.getFriendList(user_id);
		mav.addObject("friends", frndList);
		
		List<FriendModel> notAccpList = userBO.getNotAccpList(user_id);
		mav.addObject("notaccps", notAccpList);
		
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addfriend")
	@ResponseBody
	@InterceptorCheck(session=true)
	public String addFriend(@RequestParam(required=false) String frnd_id, HttpSession session) throws Exception {
		String user = (String) session.getAttribute("user");
		JSONObject result = new JSONObject();
		String msg;
		
		if(StringUtils.equals(user, frnd_id)) {
			result.put("result", "fail");
			result.put("msg", "자신을 추가할 수 없습니다.");
			return result.toJSONString();
		}
		
		FriendModel frndModel = new FriendModel();
		frndModel.setUser_id(user);
		frndModel.setFrnd_id(frnd_id);
		
		boolean isSuccess = userBO.addFriend(frndModel);
		
		if(isSuccess == true) {
			result.put("result", "success");
			result.put("msg", "성공했습니다");
			return result.toJSONString();
		}
		
		result.put("result", "fail");
		msg = URLEncoder.encode(message.getMessage("test", null, Locale.getDefault()), "UTF-8");
		result.put("msg", msg);
		//message.getMessage("test", null, Locale.KOREA);
		return result.toJSONString();
	}
	
	@RequestMapping("accpfrnd")
	@ResponseBody
	@InterceptorCheck(session=true)
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