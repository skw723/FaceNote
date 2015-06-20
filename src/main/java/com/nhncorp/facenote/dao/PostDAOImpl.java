package com.nhncorp.facenote.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
		//TODO 파일명만 같은 파일이 올라오면 현재는 덮어써짐.... 첨부파일 테이블 필요.. file명으로 구분이 아닌 유니크한 값(time)으로 구분해야할듯
		File imageFile = new File(imageFilePath + File.separator + file.getOriginalFilename());
		if(CustomFileUtils.createFile(imageFile));
		file.transferTo(imageFile);
	}

	@Override
	public File getImageFile(String fileName) throws IOException {
		File imageFile = new File(imageFilePath + File.separator + fileName);
		return imageFile;
	}
}
