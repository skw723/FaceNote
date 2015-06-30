package com.nhncorp.facenote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nhncorp.facenote.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class DatabaseTest {
	@Autowired
	SqlSessionTemplate session;
	
	@Test
	public void connectionTest() {
		UserModel userModel = new UserModel();
		userModel.setUser_id("skw723");
		int result = session.selectOne("user.isExistUser", userModel);
		
		assert(result == 1);
	}
}
