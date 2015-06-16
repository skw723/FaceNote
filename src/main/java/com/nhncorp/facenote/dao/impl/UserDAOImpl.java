package com.nhncorp.facenote.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nhncorp.facenote.dao.UserDAO;
import com.nhncorp.facenote.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	private final int FRIENDS_COUNT_LIMIT = 10000;

	@Override
	public List<User> getUserList() {
		// TODO 임의로 사용자 생성
		List<User> userList = new ArrayList<User>();
		userList.add(new User("normal1", Arrays.asList(new String[]{"normal2", "heavy1"})));
		userList.add(new User("normal2", Arrays.asList(new String[]{"normal1", "heavy1"})));
		
		List<String> heavyFriends = new ArrayList<String>();
		for (int i = 0; i < FRIENDS_COUNT_LIMIT; i++) {
			heavyFriends.add("friend_" + i);
		}
		
		//normal1 add
		heavyFriends.add("normal1");
		
		userList.add(new User("heavy1", heavyFriends));
		
		return userList;
	}

}
