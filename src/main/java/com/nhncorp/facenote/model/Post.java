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
	private Date createTime;
	private User writer;
	private String receiverId;
	private String content;
	
	public Post(){
		
	}
	
	public Post(Date createTime, User writer, String receiverId, String content) {
		super();
		this.createTime = createTime;
		this.writer = writer;
		this.receiverId = receiverId;
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
		return writer.getUserId();
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public User getWriter() {
		return writer;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

}