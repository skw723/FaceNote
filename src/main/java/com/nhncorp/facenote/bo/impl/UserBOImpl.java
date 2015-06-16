package com.nhncorp.facenote.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhncorp.facenote.bo.UserBO;
import com.nhncorp.facenote.dao.UserDAO;
import com.nhncorp.facenote.model.User;

@Service
public class UserBOImpl implements UserBO {
	@Autowired
	private UserDAO user;
	
	@Override
	public List<User> getUserList() {
		return user.getUserList();
	}
}
