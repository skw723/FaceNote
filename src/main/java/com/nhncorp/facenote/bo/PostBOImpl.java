package com.nhncorp.facenote.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.transaction.file.FileResourceManager;
import org.apache.commons.transaction.file.ResourceManagerException;
import org.apache.commons.transaction.file.ResourceManagerSystemException;
import org.apache.commons.transaction.util.Log4jLogger;
import org.apache.commons.transaction.util.LoggerFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.PostFactory;
import com.nhncorp.facenote.dao.PostDAO;
import com.nhncorp.facenote.model.Post;

@Service
public class PostBOImpl implements PostBO {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private UserBO userBO;

	private static final Logger logger = Logger.getLogger(PostBOImpl.class);

	@Override
	public List<Post> getPostList(String userId) throws IOException {
		if(!userBO.isExistUser(userId)) {
			logger.info("=== getPostList -> could not find userId ===");
			return null;
		}

		List<Post> postList = getMyTimelinePost(userId); 
		if(postList == null) {
			logger.info("=== getPostList -> getMyTimelinePost all fail ===");
			return null;
		}
		Collections.reverse(postList);
		return postList;
	}

	private List<Post> getMyTimelinePost(String userId) throws IOException {
		List<String> postStrList = postDAO.getPostList();
		List<Post> postList = new ArrayList<Post>();
		List<String> friends = userBO.getFriendList(userId);
		int failCount = 0;

		for(String postStr : postStrList) {
			String[] params = PostFactory.createPrams(postStr);
			String writer = params[Post.WRITER_POS];

			if(writer.equals(userId) || friends.contains(writer)) {
				Post post = PostFactory.createPost(params);
				if(post == null) {
					failCount++;
					continue;
				}
				postList.add(post);
			}
		}
		
		logger.info(String.format("=== createPost fail count : %d ===", failCount));
		//TODO 모두 실패한 경우만 fail처리...
		if(postStrList.size() == failCount) {
			return null;
		}
		return postList;
	}

	@Override
	public boolean writePost(String writer, String content, MultipartFile file)
			throws IOException {
		if(!userBO.isExistUser(writer)) {
			return false;
		}

		String[] params = PostFactory.createPrams(writer, content, file);
		Post post = PostFactory.createPost(params);
		
		if(post == null) {
			return false;
		}
		
		postDAO.writePost(post);
		
		if(!file.isEmpty()) {
			postDAO.saveFile(file);
		}
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
}
