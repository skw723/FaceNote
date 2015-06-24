package com.nhncorp.facenote.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.transaction.file.FileResourceManager;
import org.apache.commons.transaction.file.ResourceManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.Post;
import com.nhncorp.facenote.utils.CustomFileUtils;

@Repository
public class PostDAOImpl implements PostDAO {
	@Value("#{common['post.filename']}")
	private String postFileName;
	@Value("#{common['post.filepath']}")
	private String postFilePath;
	@Value("#{common['image.filepath']}")
	private String imageFilePath;

	@Override
	public void writePost(Post post) throws IOException {
		File postFile = new File(postFilePath + File.separator + postFileName);

		if(CustomFileUtils.createFile(postFile)) {

		}

		FileUtils.write(postFile, post.toString() + Post.NEW_LINE, true);
	}

	@Override
	public List<String> getPostList() throws IOException {
		List<String> postList = new ArrayList<String>();

		File postFile = new File(postFilePath + File.separator + postFileName);
		if(CustomFileUtils.createFile(postFile)) {
			return postList;
		}
		postList = FileUtils.readLines(postFile);

		return postList;
	}

	@Override
	public void saveFile(MultipartFile file) throws IOException {
		//TODO 현재는 같은 파일명일 경우 덮어쓰는 방식. 파일명이 아닌 별도의 유니크한 값으로 관리해야 할듯.
		File imageFile = new File(imageFilePath + File.separator + file.getOriginalFilename());
		if(CustomFileUtils.createFile(imageFile));
		file.transferTo(imageFile);
	}

	@Override
	public File getImageFile(String fileName) throws IOException {
		File imageFile = new File(imageFilePath + File.separator + fileName);
		return imageFile;
	}

	@Override
	public void writePost2(Post post, FileResourceManager frm, Object txId)
			throws IOException, ResourceManagerException {
		// TODO 트랜잭션 테스트
		File postFile = new File(postFilePath + File.separator + postFileName);

		if(CustomFileUtils.createFile(postFile)) {

		}
		
		OutputStream os = frm.writeResource(txId, postFileName, true);
		IOUtils.write(post.toString() + Post.NEW_LINE, os);

		//FileUtils.write(postFile, post.toString() + Post.NEW_LINE, true);
	}
}
