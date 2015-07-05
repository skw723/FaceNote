package com.nhncorp.facenote.bo;

import java.io.File;
import java.util.Date;
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
	@Transactional
	public boolean addPost(PostModel postModel, MultipartFile file) {
		Date current = new Date();
		postModel.setFst_reg_ymd(current);
		postModel.setLst_mod_ymd(current);
		
		if(file == null) {
			return false;
		}
				
		if(postDAO.addPost(postModel) == 0) {
			return false;
		}
		
		if(!file.isEmpty()) {
			saveFile(file, postModel.getPost_no());
		}
		
		return true;
	}
	
	private boolean saveFile(MultipartFile file, long post_no) {
		AtchFileModel fileModel = new AtchFileModel();
		fileModel.setPost_no(post_no);
		fileModel.setRl_file_nm(file.getOriginalFilename());
		fileModel.setSave_file_nm(String.valueOf((System.nanoTime())));
		//TODO long -> int 형변환
		fileModel.setFile_sz((int)file.getSize());
		fileModel.setReg_ymdt(new Date());
		File imageFile = new File(imageFilePath + File.separator + fileModel.getSave_file_nm());
		//TODO 파일저장 실패 시 예외처리 해줘야 함
		try {
			file.transferTo(imageFile);
		} catch (Exception e) {
			//TODO 혹시라도 쓰다남은 파일이 있는경우 삭제하는 로직
		}
		
		postDAO.addAtchFile(fileModel);
		return true;
	}

	@Override
	public boolean modifyPost(PostModel postModel) {
		postModel.setLst_mod_ymd(new Date());
		int result = postDAO.modifyPost(postModel);
		return result == 0 ? false : true;
	}

	@Override
	public boolean deletePost(long post_no) {
		int result = postDAO.deletePost(post_no);
		return result == 1 ? true : false;
	}
}
