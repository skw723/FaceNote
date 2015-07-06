package com.nhncorp.facenote.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	SqlSessionTemplate session;

	@Override
	public UserModel getUserInfo(String user_id) {
		return session.selectOne("user.getUserInfo", user_id); 
	}
	
	@Override
	public int addFriend(FriendModel frndModel) {
		return session.insert("user.addFriend", frndModel);
	}

	@Override
	public List<FriendModel> getFriendList(String userId) {
		return session.selectList("user.getFriendList", userId);
	}

	@Override
	public int isExistUser(String user_id) {
		return (Integer)session.selectOne("user.isExistUser", user_id);
	}

	@Override
	public int isExistFriend(FriendModel frndModel) {
		return (Integer)session.selectOne("user.isExistFrnd", frndModel);
	}

	@Override
	public int isLogin(UserModel userModel) {
		return (Integer)session.selectOne("user.isLogin", userModel);
	}

	@Override
	public List<FriendModel> getNotAccpList(String userId) {
		return session.selectList("user.getNotAccpList", userId);
	}

	@Override
	public int accpFrnd(FriendModel frndModel) {
		return (Integer)session.update("user.accpFrnd", frndModel);
	}
}