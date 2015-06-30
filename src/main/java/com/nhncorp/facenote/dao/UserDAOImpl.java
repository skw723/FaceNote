package com.nhncorp.facenote.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;

@Repository
public class UserDAOImpl implements UserDAO {
	@Value("#{common['user.filename']}")
	private String userFileName;
	@Value("#{common['user.filepath']}")
	private String userFilePath;
	@Value("#{common['friend.filename']}")
	private String friendFileName;
	@Value("#{common['friend.filepath']}")
	private String friendFilePath;
	
	@Autowired
	SqlSessionTemplate session;

	@Override
	public UserModel getUserInfo(String user_id) {
		return session.selectOne("user.getUserInfo", user_id);
	}

	@Override
	public void addUesr(String userId) throws IOException {
		File userFile = new File(userFilePath + File.separator + userFileName);
//		FileUtils.write(userFile, userId + UserModel.NEW_LINE, true);
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
	public int isExistUser(UserModel userModel) {
		return session.selectOne("user.isExistUser", userModel);
	}

	@Override
	public int isExistFriend(FriendModel frndModel) {
		return session.selectOne("user.isExistFrnd", frndModel);
	}

	@Override
	public int isLogin(UserModel userModel) {
		return session.selectOne("user.isLogin", userModel);
	}

	@Override
	public List<FriendModel> getNotAccpList(String userId) {
		return session.selectList("user.getNotAccpList", userId);
	}

	@Override
	public int accpFrnd(FriendModel frndModel) {
		return session.update("user.accpFrnd", frndModel);
	}

	@Override
	public int isExistFrnd(FriendModel frndModel) {
		return session.update("user.isExistFrnd", frndModel);
	}
}