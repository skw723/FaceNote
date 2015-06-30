/*
 * @(#)User.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.facenote.model;


/**
 * @author 심규원
 */
public class UserModel {
	private String user_id;
	private String password;
	private String user_nm;
	private String bymd;
	private String email_addr;
	private String join_ymd;
	private String use_yn;

	/**
	 * default constructor
	 */
	public UserModel() {
		
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getBymd() {
		return bymd;
	}

	public void setBymd(String bymd) {
		this.bymd = bymd;
	}

	public String getEmail_addr() {
		return email_addr;
	}

	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}

	public String getJoin_ymd() {
		return join_ymd;
	}

	public void setJoin_ymd(String join_ymd) {
		this.join_ymd = join_ymd;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
}
