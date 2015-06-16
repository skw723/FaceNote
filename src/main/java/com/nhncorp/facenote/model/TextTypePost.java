package com.nhncorp.facenote.model;

import java.util.Date;

public class TextTypePost extends Post {
	public TextTypePost(){
		super();
	}
	public TextTypePost(String[] params) {
		super(new Date(Long.parseLong(params[1])), new User(params[2]), params[3], params[4]);
	}

	@Override
	public String toString() {
		String returnString = getClass().getName() + FIELD_SEPERATOR 
				+ getCreateTime().getTime() + FIELD_SEPERATOR
				+ getWriterId() + FIELD_SEPERATOR
				+ getReceiverId() + FIELD_SEPERATOR
				+ getContent();
		
		return returnString;
	}
}
