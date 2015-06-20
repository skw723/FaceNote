package com.nhncorp.facenote.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.Post;

public interface PostDAO {
	public void writePost(Post post) throws IOException;
	public List<String> getPostList() throws IOException;
	public void saveFile(MultipartFile file) throws IOException;
	public File getImageFile(String fileName) throws IOException;
}
