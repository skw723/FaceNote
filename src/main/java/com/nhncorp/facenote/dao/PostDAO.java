package com.nhncorp.facenote.dao;

import java.util.List;

import com.nhncorp.facenote.model.Post;

public interface PostDAO {
	public boolean writeNormalUserPost(List<String> friends, Post post);
	public boolean writeHeavyUserPost(Post post);
	public List<Post> getNormalTimelineList(String userId);
	public List<Post> getHeavyTimelineList(String userId);
}
