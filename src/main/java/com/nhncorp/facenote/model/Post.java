/*
 * @(#)Post.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.facenote.model;

import java.util.Date;

import com.nhncorp.facenote.PostType;

/**
 * @author 서상우
 */
public abstract class Post {
	public static String FIELD_SEPERATOR = "::";
	public static final String NEW_LINE = "\r\n";
	private PostType type;
	private Date createTime;
	private String writer;
	private String content;
	
	public Post(){
	}
	
	public Post(PostType type, Date createTime, String writer, String content) {
		super();
		this.type = type;
		this.createTime = createTime;
		this.writer = writer;
		this.content = content;
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

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String returnString = getType() + FIELD_SEPERATOR 
				+ getCreateTime().getTime() + FIELD_SEPERATOR
				+ getWriterId() + FIELD_SEPERATOR
				+ getContent() + FIELD_SEPERATOR;
		
		return returnString;
	}
}