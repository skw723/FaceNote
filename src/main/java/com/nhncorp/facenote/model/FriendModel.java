package com.nhncorp.facenote.model;

import java.util.Date;


public class FriendModel {
	private String user_id;
	private String frnd_id;
	private String accp_yn;
	private Date aply_ymdt;
	private Date accp_ymdt;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFrnd_id() {
		return frnd_id;
	}
	public void setFrnd_id(String frnd_id) {
		this.frnd_id = frnd_id;
	}
	public String getAccp_yn() {
		return accp_yn;
	}
	public void setAccp_yn(String accp_yn) {
		this.accp_yn = accp_yn;
	}
	public Date getAply_ymdt() {
		return aply_ymdt;
	}
	public void setAply_ymdt(Date aply_ymdt) {
		this.aply_ymdt = aply_ymdt;
	}
	public Date getAccp_ymdt() {
		return accp_ymdt;
	}
	public void setAccp_ymdt(Date accp_ymdt) {
		this.accp_ymdt = accp_ymdt;
	}
}
