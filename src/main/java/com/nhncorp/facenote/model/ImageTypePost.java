package com.nhncorp.facenote.model;

import java.util.Date;

import com.nhncorp.facenote.PostType;

public class ImageTypePost extends Post {
	String imageUrl;
	
	public ImageTypePost() {
		super.setType(PostType.IMAGE);
	}
	
	public ImageTypePost(String[] params) {
		super(PostType.IMAGE, new Date(Long.parseLong(params[1])), params[2], params[3]);
		this.imageUrl = params[4];
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		String returnString = super.toString()
				+ getImageUrl();
		
		return returnString;
	}
}
