package com.nhncorp.facenote;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.facenote.model.Post;

public class PostFactory {
	private static final Logger logger = Logger.getLogger(PostFactory.class);
	
	public static Post createPost(String[] params) {
		Post post = null;
		try {
			post = new Post(params);			
		} catch(NumberFormatException e) {
			logger.info("=== createPost -> NumberFormatException ===");
			logger.info(e.getMessage());
		}
		return post;
	}
	
	public static String[] createPrams(String userId, String content, MultipartFile file) {
		String[] params = new String[4];
		params[0] = String.valueOf(new Date().getTime());
		params[1] = userId;
		params[2] = content;
		params[3] = "";
		if(!file.isEmpty()) {
			params[3] = file.getOriginalFilename();
		}
		return params;
	}
	
	public static String[] createPrams(String parmasStr) {
		String[] params = new String[4];
		String[] split = parmasStr.split(Post.FIELD_SEPERATOR);
		params[0] = split[0];
		params[1] = split[1];
		params[2] = split[2];
		params[3] = "";
		if(split.length == 4) {
			params[3] = split[3];
		}
		return params;
	}
}