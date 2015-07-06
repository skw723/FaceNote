package com.nhncorp.facenote.bo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhncorp.facenote.dao.UserDAO;
import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

@Service
public class UserBOImpl implements UserBO {
	@Autowired
	private UserDAO userDAO;
	
	private static final Logger logger = Logger.getLogger(UserBOImpl.class);
	
	@Override
	public UserModel getUserInfo(String user_id) {
		return userDAO.getUserInfo(user_id);
	}
	
	@Override
	public boolean isLogin(UserModel userModel) {
		int result = userDAO.isLogin(userModel);
		
		if(result == 0){
			logger.info("isLogin return 0");
			return false;
		}
		return true;
	}

	@Override
	public boolean addFriend(FriendModel frndModel) {
		if(StringUtils.equals(frndModel.getUser_id(), frndModel.getFrnd_id())) {
			logger.info("user_id, frnd_id equals");
			return false;
		}
		
		UserModel userModel = new UserModel();
		userModel.setUser_id(frndModel.getFrnd_id());
		
		if(isExistUser(userModel.getUser_id()) == true) {
			logger.info(userModel.getUser_id() + " not exist");
			return false;
		}
		
		//이미 친구 관계인지 확인(없거나 사용하지 않는 상태)
		if(userDAO.isExistFriend(frndModel) != 0) {
			logger.info(frndModel.getUser_id() + " and" + frndModel.getFrnd_id() + " already friends");
			return false;
		}
		
		userDAO.addFriend(frndModel);			
		return true;
	}

	@Override
	public List<FriendModel> getFriendList(String userId) {		
		return userDAO.getFriendList(userId);
	}

	@Override
	public boolean isLoginState(HttpSession session) {
		String user = (String) session.getAttribute("user");
		
		if(StringUtils.isEmpty(user)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isExistUser(String userId) {
		int result = userDAO.isExistUser(userId);
		
		if(result == 0) {
			logger.info(userId + " not exist");
			return false;
		}
		
		return true;
	}

	@Override
	public List<FriendModel> getNotAccpList(String userId) {
		return userDAO.getNotAccpList(userId);
	}

	@Override
	public boolean accpFrnd(FriendModel frndModel) {
		int result = userDAO.accpFrnd(frndModel);
		
		if(result == 0) {
			logger.info("accpFrnd fail");
			return false;
		}
		
		return true;
	}
}