package com.nhncorp.facenote.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.AtchFileModel;
import com.nhncorp.facenote.model.PostModel;

@Repository
public class PostDAOImpl implements PostDAO {
	@Value("#{common['post.filename']}")
	private String postFileName;
	@Value("#{common['post.filepath']}")
	private String postFilePath;
	@Value("#{common['image.filepath']}")
	private String imageFilePath;
	
	@Autowired
	SqlSessionTemplate session;

	@Override
	public void writePost(PostModel post) throws IOException {
//		File postFile = new File(postFilePath + File.separator + postFileName);
//		FileUtils.write(postFile, post.toString() + PostModel.NEW_LINE, true);
	}

	@Override
	public List<PostModel> getPostList(String user_id) {
		return session.selectList("post.getPostList", user_id);
	}

	@Override
	public void saveFile(MultipartFile file) throws IOException {
		//TODO 현재는 같은 파일명일 경우 덮어쓰는 방식. 파일명이 아닌 별도의 유니크한 값으로 관리해야 할듯.
		File imageFile = new File(imageFilePath + File.separator + file.getOriginalFilename());
		file.transferTo(imageFile);
	}

	@Override
	public File getImageFile(String fileName) throws IOException {
		File imageFile = new File(imageFilePath + File.separator + fileName);
		return imageFile;
	}

	@Override
	public int addPost(PostModel postModel) {
		return session.insert("post.addPost", postModel);
	}

	@Override
	public int addAtchFile(AtchFileModel fileModel) {
		return session.insert("post.addAtchFile", fileModel); 
	}

	@Override
	public int modifyPost(PostModel postModel) {
		return session.update("post.modifyPost", postModel);
	}

	/*@Override
	public void writePost2(Post post, FileResourceManager frm, Object txId)
			throws IOException, ResourceManagerException {
		// TODO 트랜잭션 테스트
		File postFile = new File(postFilePath + File.separator + postFileName);

		if(CustomFileUtils.createDirectory(postFile)) {

		}
		
		OutputStream os = frm.writeResource(txId, postFileName, true);
		IOUtils.write(post.toString() + Post.NEW_LINE, os);

		//FileUtils.write(postFile, post.toString() + Post.NEW_LINE, true);
	}*/
}
