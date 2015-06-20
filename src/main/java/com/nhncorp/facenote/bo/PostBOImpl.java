package com.nhncorp.facenote.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
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
			String[] split = postStr.split(Post.FIELD_SEPERATOR);
			String writer = split[2];

			if(writer.equals(userId) || friends.contains(writer)) {
				Post post = PostFactory.createPost(split);
				if(post == null) {
					failCount++;
					continue;
				}
				postList.add(post);
			}
		}
		
		//TODO 실패한 것들은 어떻게???
		logger.info(String.format("=== createPost fail count : %d ===", failCount));
		
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
		//TODO Post 등록과 첨부파일 저장 트랜잭션 처리...??
		postDAO.writePost(post);
		
		if(!file.isEmpty()) {
			postDAO.saveFile(file);
		}
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
