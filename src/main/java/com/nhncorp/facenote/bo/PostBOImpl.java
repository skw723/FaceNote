package com.nhncorp.facenote.bo;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.dao.PostDAO;
import com.nhncorp.facenote.model.AtchFileModel;
import com.nhncorp.facenote.model.PostModel;

@Service
public class PostBOImpl implements PostBO {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private UserBO userBO;
	@Value("#{common['image.filepath']}")
	private String imageFilePath;

	private static final Logger logger = Logger.getLogger(PostBOImpl.class);

	@Override
	public List<PostModel> getPostList(String userId) {
		return postDAO.getPostList(userId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean addPost(PostModel postModel, MultipartFile file) throws Exception {
		if(postDAO.addPost(postModel) == 0) {
			logger.error("addPost return 0");
			return false;
		}
		
		if(file != null && !file.isEmpty()) {
			saveFile(file, postModel.getPost_no());
		}
		
		return true;
	}
	
	private boolean saveFile(MultipartFile file, long post_no) throws Exception {
		AtchFileModel fileModel = new AtchFileModel();
		fileModel.setPost_no(post_no);
		fileModel.setRl_file_nm(file.getOriginalFilename());
		fileModel.setSave_file_nm(String.valueOf((System.nanoTime())));
		fileModel.setFile_sz((int)file.getSize());
		File imageFile = new File(imageFilePath + File.separator + fileModel.getSave_file_nm());
		
		try {
			file.transferTo(imageFile);
		} catch (Exception e) {
			logger.error("File Save Fail");
			throw new Exception();
		}
		
		if(postDAO.addAtchFile(fileModel) == 0) {
			logger.error("addAtchFile return 0");
			return false;
		}
		return true;
	}

	@Override
	public boolean modifyPost(PostModel postModel) {
		int result = postDAO.modifyPost(postModel);
		
		if(result == 0) {
			logger.error("modifyPost return 0");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deletePost(long post_no) {
		int result = postDAO.deletePost(post_no);
		
		if(result == 0) {
			logger.error("deletePost return 0");
			return false;
		}

		return true;
	}
}
