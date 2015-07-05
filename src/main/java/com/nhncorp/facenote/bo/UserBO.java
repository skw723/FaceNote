package com.nhncorp.facenote.bo;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

public interface UserBO {
	public UserModel getUserInfo(String user_id);
	public boolean addFriend(FriendModel frndModel);
	public List<FriendModel> getFriendList(String userId);
	public List<FriendModel> getNotAccpList(String userId);
	public boolean isLogin(UserModel userModel);
	public boolean isLoginState(HttpSession session);
	public boolean isExistUser(String userId);
	public boolean accpFrnd(FriendModel frndModel);
}
