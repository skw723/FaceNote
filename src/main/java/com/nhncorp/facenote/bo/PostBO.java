package com.nhncorp.facenote.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.Post;

public interface PostBO {
	public boolean writePost(String writer, String content, MultipartFile file) throws IOException;
	public List<Post> getPostList(String userId) throws IOException;
	public byte[] getImageFileByte(String fileName) throws IOException;
}
