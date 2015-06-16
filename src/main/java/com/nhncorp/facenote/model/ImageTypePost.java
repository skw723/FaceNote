package com.nhncorp.facenote.model;

import java.util.Date;

public class ImageTypePost extends Post {
	String imageUrl;
	
	public ImageTypePost() {
		
	}
	
	public ImageTypePost(String[] params) {
		super(new Date(Long.parseLong(params[1])), new User(params[2]), params[3], params[4]);
		this.imageUrl = params[5];
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		String returnString = getClass().getName() + FIELD_SEPERATOR 
				+ getCreateTime().getTime() + FIELD_SEPERATOR
				+ getWriterId() + FIELD_SEPERATOR
				+ getReceiverId() + FIELD_SEPERATOR
				+ getContent() + FIELD_SEPERATOR
				+ getImageUrl();
		
		return returnString;
	}
}
