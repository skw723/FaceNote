package com.nhncorp.facenote.bo;

import java.io.IOException;
import java.util.List;

import com.nhncorp.facenote.model.User;

public interface UserBO {
	public List<User> getUserList() throws IOException;
	public boolean addUser(String userId) throws IOException;
	public boolean addFriend(String userId, String friendId) throws IOException;
	public List<String> getFriendList(String userId) throws IOException;
	public boolean isExistUser(String userId) throws IOException;
}
