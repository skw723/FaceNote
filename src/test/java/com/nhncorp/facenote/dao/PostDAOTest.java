package com.nhncorp.facenote.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nhncorp.facenote.model.AtchFileModel;
import com.nhncorp.facenote.model.PostModel;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PostDAOTest {
	@Autowired
	PostDAO postDAO;

	@Test
	public void testGetPostList() {
		String userId = "skw723";
		
		List<PostModel> result = postDAO.getPostList(userId);
		
		assert(result.size() != 0);
	}

	@Test
	@Transactional
	public void testAddPost() {
		PostModel postModel = new PostModel();
		postModel.setUser_id("skw723");
		postModel.setPost_cont("test");
		
		List<PostModel> before = postDAO.getPostList("skw723");
		postDAO.addPost(postModel);
		List<PostModel> after = postDAO.getPostList("skw723");
		
		assert((after.size() - before.size()) == 1);
	}

	@Test
	@Transactional
	public void testAddAtchFile() {
		AtchFileModel fileModel = new AtchFileModel();
		fileModel.setFile_sz(1);
		fileModel.setPost_no(0);
		fileModel.setRl_file_nm("test.jpg");
		fileModel.setSave_file_nm(String.valueOf(System.nanoTime()));
		
		int result = postDAO.addAtchFile(fileModel);
		
		assert(result == 1);
	}

	@Test
	@Transactional
	public void testModifyPost() {
		List<PostModel> beforeList = postDAO.getPostList("skw723");
		PostModel target = beforeList.get(0);
		
		target.setPost_cont("수정수정!!");
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
