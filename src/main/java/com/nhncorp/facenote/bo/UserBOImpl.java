package com.nhncorp.facenote.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhncorp.facenote.dao.UserDAO;
import com.nhncorp.facenote.model.Friendship;
import com.nhncorp.facenote.model.User;

@Service
public class UserBOImpl implements UserBO {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> getUserList() throws IOException {
		List<String> userStrList = userDAO.getUserList();
		List<User> userList = new ArrayList<User>();

		for(String userId : userStrList) {
			User user = new User(userId);
			user.setFriends(getFriendList(userId));
			userList.add(user);
		}
		return userList;
	}

	@Override
	public boolean addUser(String userId) throws IOException {
		if(isExistUser(userId)) {
			return false;			
		}
		userDAO.addUesr(userId);
		return true;
	}
	
	@Override
	public boolean isExistUser(String userId) throws IOException {
		List<User> userList = getUserList();
		for(User user : userList) {
			if(user.getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addFriend(String userId, String friendId) throws IOException {
		if(!isExistUser(userId) || !isExistUser(friendId)) {
			return false;
		}
		
		if(isExistFriend(userId, friendId)) {
			//TODO 이미 등록된 친구의 경우 현재는 성공으로 처리
			return true;
		}
		
		userDAO.addFriend(userId, friendId);
		
		return true;
	}
	
	private boolean isExistFriend(String userId, String friendId) throws IOException {
		List<String> friendList = getFriendList(userId);
		
		for(String id : friendList) {
			if(id.equals(friendId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> getFriendList(String userId) throws IOException {
		List<String> friends = new ArrayList<String>();
		List<String> friendStrList = userDAO.getFriendList(userId);
		
		for(String str : friendStrList) {
			String[] split =  str.split(Friendship.separator);
			if(split[0].equals(userId)) {
				friends.add(split[1]);
			} else if(split[1].equals(userId)) {
				friends.add(split[0]);
			}
		}
		
		return friends;
	}
}