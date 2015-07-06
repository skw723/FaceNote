/*
 * @(#)Post.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.facenote.model;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 심규원
 */
public class PostModel {
	private long post_no;
	private String user_id;
	private String user_nm;
	private String post_cont;
	private String save_file_nm;
	private Date fst_reg_ymdt;
	private Date lst_mod_ymdt;
	private String formattedDate;
	private String use_yn;
	
	public PostModel(){
		
	}

	public long getPost_no() {
		return post_no;
	}

	public void setPost_no(long post_no) {
		this.post_no = post_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getPost_cont() {
		return post_cont;
	}

	public void setPost_cont(String post_cont) {
		this.post_cont = post_cont;
	}

	public String getSave_file_nm() {
		return save_file_nm;
	}

	public void setSave_file_nm(String save_file_nm) {
		this.save_file_nm = save_file_nm;
	}

	public Date getFst_reg_ymdt() {
		return fst_reg_ymdt;
	}

	public void setFst_reg_ymdt(Date fst_reg_ymdt) {
		this.fst_reg_ymdt = fst_reg_ymdt;
	}

	public Date getLst_mod_ymdt() {
		return lst_mod_ymdt;
	}

	public void setLst_mod_ymdt(Date lst_mod_ymdt) {
		this.lst_mod_ymdt = lst_mod_ymdt;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.formattedDate = format.format(lst_mod_ymdt);
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
	
}