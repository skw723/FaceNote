/*
 * @(#)User.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.facenote.model;

import java.util.List;

/**
 * @author 서상우
 */
public class User {
	public static final int FRIENDS_COUNT_LIMIT = 10000;
	public static final String NEW_LINE = "\r\n";
	String userId;
	List<String> friends;

	/**
	 * default constructor
	 */
	public User() {
	}

	/**
	 * @param string
	 */
	public User(String userId) {
		this.userId = userId;
	}

	/**
	 * @param string
	 * @param friends
	 */
	public User(String userId, List<String> friends) {
		this.userId = userId;
		this.friends = friends;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
}
