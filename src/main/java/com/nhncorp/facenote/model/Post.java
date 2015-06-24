/*
 * @(#)Post.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.facenote.model;

import java.util.Date;

/**
 * @author 서상우
 */
public class Post {
	public static String FIELD_SEPERATOR = "::";
	public static final String NEW_LINE = "\r\n";
	public static final int CREATE_TIME_POS = 0;
	public static final int WRITER_POS = 1;
	public static final int CONTENT_POS = 2;
	public static final int IMAGE_URL_POS = 3;
	private Date createTime;
	private String writer;
	private String content;
	private String imageName;
	
	public Post(){
	}
	
	public Post(String[] params) throws NumberFormatException {
		this.createTime = new Date(Long.parseLong(params[0]));
		this.writer = params[1];
		this.content = params[2];
		this.imageName = params[3];
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getWriterId() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriter() {
		return writer;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		String returnString = 
				getCreateTime().getTime() + FIELD_SEPERATOR + 
				getWriterId() + FIELD_SEPERATOR + 
				getContent() + FIELD_SEPERATOR +
				getImageName() + FIELD_SEPERATOR;
		
		return returnString;
	}
}