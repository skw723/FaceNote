package com.nhncorp.facenote.model;

public class AtchFileModel {
	private long atch_img_no;
	private long post_no;
	private String rl_file_nm;
	private String save_file_nm;
	private int file_sz;
	private String use_yn;
	public long getAtch_img_no() {
		return atch_img_no;
	}
	public void setAtch_img_no(long atch_img_no) {
		this.atch_img_no = atch_img_no;
	}
	public long getPost_no() {
		return post_no;
	}
	public void setPost_no(long post_no) {
		this.post_no = post_no;
	}
	public String getRl_file_nm() {
		return rl_file_nm;
	}
	public void setRl_file_nm(String rl_file_nm) {
		this.rl_file_nm = rl_file_nm;
	}
	public String getSave_file_nm() {
		return save_file_nm;
	}
	public void setSave_file_nm(String save_file_nm) {
		this.save_file_nm = save_file_nm;
	}
	public int getFile_sz() {
		return file_sz;
	}
	public void setFile_sz(int file_sz) {
		this.file_sz = file_sz;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
}
