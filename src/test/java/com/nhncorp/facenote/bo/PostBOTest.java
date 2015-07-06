package com.nhncorp.facenote.bo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.PostModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PostBOTest {
	@Autowired
	PostBO postBO;

	@Test
	public void testGetPostList() {
		String userId = "skw723";
		
		List<PostModel> list = postBO.getPostList(userId);
		
		assert(list.size() != 0);
	}

	@Test
	@Transactional
	public void testAddPost() throws Exception {
		PostModel postModel = new PostModel();
		postModel.setUser_id("skw723");
		postModel.setPost_cont("test");
		MultipartFile file = new MockMultipartFile("test.jpg","test".getBytes());
		
		assert(postBO.addPost(postModel, file));
	}

	@Test
	@Transactional
	public void testModifyPost() {
		PostModel postModel = new PostModel();
		postModel.setUser_id("skw723");
		postModel.setPost_cont("test1");
		
		assert(postBO.modifyPost(postModel));
	}

	@Test
	public void testDeletePost() {
		List<PostModel> beforeList = postBO.getPostList("skw723");
		PostModel target = beforeList.get(0);
		
		postBO.deletePost(target.getPost_no());
		List<PostModel> afterList = postBO.getPostList("skw723");
		
		assert(afterList.size() - beforeList.size() == 1);
	}

}
