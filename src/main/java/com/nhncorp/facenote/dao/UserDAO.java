package com.nhncorp.facenote.dao;

import java.io.IOException;
import java.util.List;

public interface UserDAO {
	public List<String> getUserList() throws IOException;
	public void addUesr(String UserId) throws IOException;
	public void addFriend(String userId, String friendId) throws IOException;
	public List<String> getFriendList(String userId) throws IOException;
}