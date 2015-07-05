package com.nhncorp.facenote.bo;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhncorp.facenote.dao.UserDAO;
import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

@Service
public class UserBOImpl implements UserBO {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserModel getUserInfo(String user_id) {
		return userDAO.getUserInfo(user_id);
	}
	
	@Override
	public boolean isLogin(UserModel userModel) {
		int result = userDAO.isLogin(userModel);
		
		if(result == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean addFriend(FriendModel frndModel) {
		UserModel userModel = new UserModel();
		userModel.setUser_id(frndModel.getFrnd_id());
		frndModel.setAply_ymdt(new Date());
		
		if(userDAO.isExistUser(userModel) == 0) {
			return false;
		}
		
		//TODO 이미 친구 관계인지 확인(없거나 사용하지 않는 상태)
		if(userDAO.isExistFriend(frndModel) != 0) {
			return false;
		}
		
		userDAO.addFriend(frndModel);			
		/*try {
		} catch(DuplicateKeyException e) {
			return false;
		}*/
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
		return false;
	}

	@Override
	public List<FriendModel> getNotAccpList(String userId) {
		return userDAO.getNotAccpList(userId);
	}

	@Override
	public boolean accpFrnd(FriendModel frndModel) {
		frndModel.setAccp_ymdt(new Date());
		int result = userDAO.accpFrnd(frndModel);
		return result == 0 ? false : true;
	}
}