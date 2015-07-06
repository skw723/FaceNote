package com.nhncorp.facenote.bo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nhncorp.facenote.model.FriendModel;
import com.nhncorp.facenote.model.UserModel;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserBOTest {
	@Autowired
	UserBO userBO;

	@Test
	public void testGetUserInfo() {
		String userId = "skw723";

		UserModel result = userBO.getUserInfo(userId);

		assertEquals(userId, result.getUser_id());
	}

	@Test
	public void testIsLogin() {
		UserModel userModel = new UserModel();
		userModel.setUser_id("skw723");
		userModel.setPassword("1234");

		boolean result = userBO.isLogin(userModel);

		assert(result);
	}

	@Test
	public void testAddFriend() {
		FriendModel frndModel = new FriendModel();
		frndModel.setFrnd_id("skw723");
		frndModel.setUser_id("test1");

		boolean result = userBO.addFriend(frndModel);

		assert(result);
	}

	@Test
	public void testGetFriendList() {
		String userId = "skw723";
		List<FriendModel> result = userBO.getFriendList(userId);

		assert(result.size() != 0);
	}

	@Test
	public void testIsLoginState() {
		//TODO 세션 세팅
		HttpSession session = new MockHttpSession();
		session.setAttribute("user", "skw723");
		boolean result = userBO.isLoginState(session);

		assert(result);
	}

	@Test
	public void testIsExistUser() {
		String userId = "skw723";

		boolean result = userBO.isExistUser(userId);

		assert(result);
	}

	@Test
	public void testGetNotAccpList() {
		String userId = "skw723";

		List<FriendModel> result = userBO.getNotAccpList(userId);

		assert(result.size() == 1);
	}

	@Test
	public void testAccpFrnd() {
		FriendModel frndModel = new FriendModel();
		frndModel.setFrnd_id("skw723");
		frndModel.setUser_id("test1");

		boolean result = userBO.accpFrnd(frndModel);

		assert(result);
	}
}
