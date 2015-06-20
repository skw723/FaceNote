package com.nhncorp.facenote;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.ImageTypePost;
import com.nhncorp.facenote.model.Post;
import com.nhncorp.facenote.model.TextTypePost;

public class PostFactory {
	private static final Logger logger = Logger.getLogger(PostFactory.class);
	
	public static Post createPost(String[] params) {
		PostType postType;
		
		try{
			int postCode = Integer.parseInt(params[0]);
			postType = PostType.getPostType(postCode);
		} catch (NumberFormatException e) {
			logger.info("=== createPost -> postCode parse error ===");
			return null;
		}
		
		if(PostType.TEXT == postType) {
			return new TextTypePost(params);
		} else if(PostType.IMAGE == postType) {
			return new ImageTypePost(params);
		}
		logger.info("=== createPost -> could not find postType ===");
		return null;
	}
	
	public static String[] createPrams(String userId, String content, MultipartFile file) {
		String[] params = new String[5];
		params[1] = String.valueOf(new Date().getTime());
		params[2] = userId;
		params[3] = content;
		if(file.isEmpty()) {
			params[0] = String.valueOf(PostType.TEXT.getTypeCode());
		} else {
			params[0] = String.valueOf(PostType.IMAGE.getTypeCode());
			params[4] = file.getOriginalFilename();
		}
		return params;
	}
}