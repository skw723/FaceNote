package com.nhncorp.facenote.model;

import java.util.Date;

import com.nhncorp.facenote.PostType;

public class TextTypePost extends Post {
	public TextTypePost(){
		super.setType(PostType.TEXT);
	}
	public TextTypePost(String[] params) {
		super(PostType.TEXT, new Date(Long.parseLong(params[1])), params[2], params[3]);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
