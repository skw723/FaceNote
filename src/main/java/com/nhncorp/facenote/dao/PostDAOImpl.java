package com.nhncorp.facenote.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhncorp.facenote.model.AtchFileModel;
import com.nhncorp.facenote.model.PostModel;

@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired
	SqlSessionTemplate session;

	@Override
	public List<PostModel> getPostList(String user_id) {
		return session.selectList("post.getPostList", user_id);
	}

	@Override
	public int addPost(PostModel postModel) {
		return (Integer)session.insert("post.addPost", postModel);
	}

	@Override
	public int addAtchFile(AtchFileModel fileModel) {
		return (Integer)session.insert("post.addAtchFile", fileModel); 
	}

	@Override
	public int modifyPost(PostModel postModel) {
		return (Integer)session.update("post.modifyPost", postModel);
	}
	@Override
	public int deletePost(long post_no) {
		return (Integer)session.update("post.deletePost", post_no);
	}

	@Override
	public PostModel getPostOne(long post_no) {
		return (PostModel)session.selectOne("post.getPostOne", post_no);
	}
}
