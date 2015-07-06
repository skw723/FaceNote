package com.nhncorp.facenote.bo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.PostModel;

public interface PostBO {
	public List<PostModel> getPostList(String userId);
	public boolean addPost(PostModel postModel, MultipartFile File) throws Exception;
	public boolean modifyPost(PostModel postModel);
	public boolean deletePost(long post_no);
}
