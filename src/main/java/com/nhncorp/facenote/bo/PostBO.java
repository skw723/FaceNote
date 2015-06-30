package com.nhncorp.facenote.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.PostModel;

public interface PostBO {
	public boolean writePost(String writer, String content, MultipartFile file) throws IOException;
	public List<PostModel> getPostList(String userId);
	public byte[] getImageFileByte(String fileName) throws IOException;
	public boolean addPost(PostModel postModel, MultipartFile File);
	public boolean modifyPost(PostModel postModel);
}
