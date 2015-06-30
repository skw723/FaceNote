package com.nhncorp.facenote.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.transaction.file.FileResourceManager;
import org.apache.commons.transaction.file.ResourceManagerException;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.AtchFileModel;
import com.nhncorp.facenote.model.PostModel;

public interface PostDAO {
	public void writePost(PostModel post) throws IOException;
	//public void writePost2(Post post, FileResourceManager frm, Object txId) throws IOException, ResourceManagerException;
	public List<PostModel> getPostList(String user_id);
	public void saveFile(MultipartFile file) throws IOException;
	public File getImageFile(String fileName) throws IOException;
	public int addPost(PostModel postModel);
	public int addAtchFile(AtchFileModel fileModel);
	public int modifyPost(PostModel postModel);
}
