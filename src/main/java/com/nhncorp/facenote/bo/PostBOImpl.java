package com.nhncorp.facenote.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

	private List<PostModel> getMyTimelinePost(String userId) throws IOException {
//		List<String> postStrList = postDAO.getPostList();
//		List<Post> postList = new ArrayList<Post>();
//		List<String> friends = userBO.getFriendList(userId);
//		int failCount = 0;
//
//		for(String postStr : postStrList) {
//			String[] params = PostFactory.createPrams(postStr);
//			String writer = params[Post.WRITER_POS];
//
//			if(writer.equals(userId) || friends.contains(writer)) {
//				Post post = PostFactory.createPost(params);
//				if(post == null) {
//					failCount++;
//					continue;
//				}
//				postList.add(post);
//			}
//		}
//		
//		logger.info(String.format("=== createPost fail count : %d ===", failCount));
//		//TODO 모두 실패한 경우만 fail처리...
//		if(postStrList.size() == failCount) {
//			return null;
//		}
//		return postList;
		return null;
	}

	@Override
	public boolean writePost(String writer, String content, MultipartFile file)
			throws IOException {
//		if(!userBO.isExistUser(writer)) {
//			return false;
//		}

//		String[] params = PostFactory.createPrams(writer, content, file);
//		PostModel post = PostFactory.createPost(params);
//		
//		if(post == null) {
//			return false;
//		}
//		
//		postDAO.writePost(post);
//		
//		if(!file.isEmpty()) {
//			postDAO.saveFile(file);
//		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//TODO Post 등록과 파일저장 트랜잭션 처리...
		/*LoggerFacade loggerFaced = null;
		FileResourceManager frm = null;
		String txId = null;
		if(!file.isEmpty()) {
			loggerFaced = new Log4jLogger(logger);
			frm = new FileResourceManager("C:\\tmp", "C:\\tmp\\work", false, loggerFaced);
			try {
				frm.start();
				txId = frm.generatedUniqueTxId();
				frm.startTransaction(txId);
			} catch (Exception e) {
				return false;
			}
			try {
				postDAO.writePost2(post, frm, txId);
				postDAO.saveFile(file);
			} catch (Exception e) {
				try {
					frm.rollbackTransaction(txId);
					//TODO : 저장하다 만 파일이 있다면 삭제하는 로직 추가
					return false;
				} catch (ResourceManagerException e1) {
					return false;
				}
			}
			try {
				frm.commitTransaction(txId);
				return true;
			} catch (ResourceManagerException e) {
				try {
					frm.rollbackTransaction(txId);
					return false;
				} catch (ResourceManagerException e1) {
					return false;
				}
			}
		}*/
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		return true;
	}

	@Override
	public byte[] getImageFileByte(String fileName) throws IOException {
		File imageFile = postDAO.getImageFile(fileName);
		if(imageFile.exists()) {
			return IOUtils.toByteArray(new FileInputStream(imageFile));
		}
		return null; 
	}

	@Override
	public boolean addPost(PostModel postModel, MultipartFile file) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		postModel.setFst_reg_ymd(date);
		postModel.setLst_mod_ymd(date);
		
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
		File imageFile = new File(imageFilePath + File.separator + fileModel.getSave_file_nm());
		//TODO 파일저장 실패 시 예외처리 해줘야 함
		try {
			file.transferTo(imageFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postDAO.addAtchFile(fileModel);
		return true;
	}

	@Override
	public boolean modifyPost(PostModel postModel) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		postModel.setLst_mod_ymd(date);
		int result = postDAO.modifyPost(postModel);
		return result == 0 ? false : true;
	}
}
