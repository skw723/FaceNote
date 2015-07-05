package com.nhncorp.facenote.dao;

import java.util.List;

import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

public interface UserDAO {
	public UserModel getUserInfo(String user_id);
	public int addFriend(FriendModel frndModel);
	public List<FriendModel> getFriendList(String userId);
	public List<FriendModel> getNotAccpList(String userId);
	public int isExistUser(UserModel userModel);
	public int isExistFrnd(FriendModel frndModel);
	public int isLogin(UserModel userModel);
	public int isExistFriend(FriendModel frndModel);
	public int accpFrnd(FriendModel frndModel);
}