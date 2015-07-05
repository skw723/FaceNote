package com.nhncorp.facenote;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.nhncorp.facenote.bo.PostBO;
import com.nhncorp.facenote.dao.PostDAO;
import com.nhncorp.facenote.model.PostModel;
import com.nhncorp.facenote.model.PostModelValidator;
import com.nhncorp.facenote.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class DatabaseTest {
	@Autowired
	SqlSessionTemplate session;
	@Autowired
	PostBO postBO;
	@Autowired 
	PostDAO postDAO;
	
	@Test
	public void testConnection() {
		UserModel userModel = new UserModel();
		userModel.setUser_id("skw723");
		int result = session.selectOne("user.isExistUser", userModel);
		
		assert(result == 1);
	}
	
	@Test
	public void testPostModelContValidation() {
		PostModel postModel = new PostModel();
		String cont = new String();
		//한글 3byte -> 333글자까지 통과, 그다음부터 fail
		for(int i=0; i<334; i++) {
			cont += "한";
		}
		postModel.setPost_cont(cont);
		BindException errors = new BindException(postModel, "postModel");
        ValidationUtils.invokeValidator(new PostModelValidator(), postModel, errors);
        assertTrue(errors.hasErrors());
    }
	
	@Test
	@Transactional
	public void testInsertPost() {
		PostModel postModel = new PostModel();
		postModel.setUser_id("skw723");
		postModel.setFst_reg_ymd(new Date());
		postModel.setLst_mod_ymd(new Date());
		postModel.setPost_cont("test");
		
		List<PostModel> before = postDAO.getPostList("skw723");
		postDAO.addPost(postModel);
		List<PostModel> after = postDAO.getPostList("skw723");
		
		assert((after.size() - before.size()) == 1);
	}
	
	@Test
	@Transactional
	public void testUpdatePost() {
		List<PostModel> beforeList = postDAO.getPostList("skw723");
		PostModel target = beforeList.get(0);
		Date mod_ymdt = new Date();
		
		target.setPost_cont("수정수정!!");
		target.setLst_mod_ymd(mod_ymdt);
		int result = postDAO.modifyPost(target);
		
		assert(result == 1);
	}
	
	@Test
	@Transactional
	public void testDeletePost() {
		List<PostModel> beforeList = postDAO.getPostList("skw723");
		PostModel target = beforeList.get(0);
		
		postDAO.deletePost(target.getPost_no());
		List<PostModel> afterList = postDAO.getPostList("skw723");
		
		assert(afterList.size() - beforeList.size() == 1);
	}
}
