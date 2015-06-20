package com.nhncorp.facenote.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.nhncorp.facenote.model.Friendship;
import com.nhncorp.facenote.model.User;
import com.nhncorp.facenote.utils.CustomFileUtils;

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

	@Override
	public List<String> getUserList() throws IOException {
		List<String> userList = new ArrayList<String>();

		File userFile = new File(userFilePath + File.separator + userFileName);
		if(CustomFileUtils.createFile(userFile)) {
			return userList;
		}
		userList = FileUtils.readLines(userFile);

		return userList;
	}

	@Override
	public void addUesr(String userId) throws IOException {
		File userFile = new File(userFilePath + File.separator + userFileName);

		if(CustomFileUtils.createFile(userFile)) {

		}

		FileUtils.write(userFile, userId + User.NEW_LINE, true);
	}

	@Override
	public void addFriend(String userId, String friendId) throws IOException {
		File userFile = new File(friendFilePath + File.separator + friendFileName);

		if(CustomFileUtils.createFile(userFile)) {

		}

		FileUtils.write(userFile, userId + Friendship.separator + friendId + User.NEW_LINE, true);
	}

	@Override
	public List<String> getFriendList(String userId) throws IOException {
		List<String> friendList = new ArrayList<String>();

		File friendFile = new File(friendFilePath + File.separator + friendFileName);
		if(CustomFileUtils.createFile(friendFile)) {
			return friendList;
		}
		friendList = FileUtils.readLines(friendFile);

		return friendList;
	}
}