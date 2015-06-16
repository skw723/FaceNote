/*
 * @(#)HeavyUserMessages.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.facenote;

import java.util.ArrayList;
import java.util.List;

import com.nhncorp.facenote.model.Post;

/**
 * @author 서상우
 */
public class HeavyUserPostRepository {
	private static HeavyUserPostRepository instance;
	private static List<Post> postsList;
	
	static {
		instance = new HeavyUserPostRepository();
	}
	
	private HeavyUserPostRepository(){
		postsList = new ArrayList<Post>();
	}
	
	public static HeavyUserPostRepository getInstance(){
		return instance;
	}
	
	/**
	 * @return
	 */
	public static List<Post> getPosts() {
		return postsList;
	}
	public void addPost(Post post){
		postsList.add(post);
	}
	public void clearAll(){
		postsList.clear();
	}
}
