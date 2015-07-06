package com.nhncorp.facenote.dao;

import java.util.List;

import com.nhncorp.facenote.model.AtchFileModel;
import com.nhncorp.facenote.model.PostModel;

public interface PostDAO {
	public List<PostModel> getPostList(String user_id);
	public int addPost(PostModel postModel);
	public int addAtchFile(AtchFileModel fileModel);
	public int modifyPost(PostModel postModel);
	public int deletePost(long post_no);
	public PostModel getPostOne(long post_no);
}
