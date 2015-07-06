package com.nhncorp.facenote.bo;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.PostModel;

public interface PostBO {
	public List<PostModel> getPostList(String userId);
	public boolean addPost(PostModel postModel, MultipartFile File) throws Exception;
	public boolean modifyPost(PostModel postModel);
	public boolean deletePost(long post_no);
	public PostModel getPostOne(long post_no);
	public Map<String, Object> getPostMap(PostModel post);
}
