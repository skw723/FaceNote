package com.nhncorp.facenote.bo;

import java.util.List;

import com.nhncorp.facenote.model.Post;
import com.nhncorp.facenote.model.User;

public interface PostBO {
	public boolean writePost(User writer, Post post);
	public List<Post> getTimelineList(String userId);
}
