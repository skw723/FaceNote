package com.nhncorp.facenote.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserDAOTest {
	@Autowired
	UserDAO userDAO;

	@Test
	public void testGetUserInfo() {
		String userId = "skw723";

		UserModel result = userDAO.getUserInfo(userId);

		assertEquals(userId, result.getUser_id());
	}

	@Test
	@Transactional
	public void testAddFriend() {
		FriendModel frndModel = new FriendModel();
		frndModel.setFrnd_id("test");
		frndModel.setUser_id("skw723");

		int result = userDAO.addFriend(frndModel);

		assert(result == 1);
	}

	@Test
	public void testGetFriendList() {
		String userId = "skw723";
		List<FriendModel> result = userDAO.getFriendList(userId);

		assert(result.size() != 0);
	}

	@Test
	public void testIsExistUser() {
		String userId = "skw723";

		int result = userDAO.isExistUser(userId);

		assert(result == 1);
	}

	@Test
	public void testIsExistFriend() {
		FriendModel frndModel = new FriendModel();
		frndModel.setFrnd_id("skw723");
		frndModel.setUser_id("test");

		int result = userDAO.isExistFriend(frndModel);

		assert(result == 1);
	}

	@Test
	public void testIsLogin() {
		UserModel userModel = new UserModel();
		userModel.setUser_id("skw723");
		userModel.setPassword("1234");

		int result = userDAO.isLogin(userModel);

		assert(result == 1);
	}

	@Test
	public void testGetNotAccpList() {
		String userId = "skw723";

		List<FriendModel> result = userDAO.getNotAccpList(userId);

		assert(result.size() == 1);
	}

	@Test
	@Transactional
	public void testAccpFrnd() {
		FriendModel frndModel = new FriendModel();
		frndModel.setFrnd_id("skw723");
		frndModel.setUser_id("skw723");

		int result = userDAO.accpFrnd(frndModel);

		assert(result == 1);
	}
}
