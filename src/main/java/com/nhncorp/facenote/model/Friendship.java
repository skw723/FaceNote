package com.nhncorp.facenote.model;


public class Friendship {
	public static final String separator = "::";
	public static final int USER1_POS = 0;
	public static final int USER2_POS = 1;
	private String user1;
	private String user2;
	
	public Friendship() {
	}
	public Friendship(String user1, String user2) {
		this.user1 = user1;
		this.user2 = user2;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
}
