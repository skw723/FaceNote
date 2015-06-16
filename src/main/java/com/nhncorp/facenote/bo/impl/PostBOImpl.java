package com.nhncorp.facenote.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhncorp.facenote.bo.PostBO;
import com.nhncorp.facenote.dao.PostDAO;
import com.nhncorp.facenote.model.Post;
import com.nhncorp.facenote.model.User;

@Service
public class PostBOImpl implements PostBO {
	@Autowired
	private PostDAO postDAO;

	@Override
	public boolean writePost(User writer, Post post) {
		List<String> friends = new ArrayList<String>();
		friends.addAll(writer.getFriends());
		// 자신의 timeline에도 post를 추가하기 위해 목록에 자신의 id 추가
		friends.add(writer.getUserId());
		// post 정보 입력(생성시간, 작성자)
		post.setCreateTime(Calendar.getInstance().getTime());
		post.setWriter(writer);
		// 노멀, 헤비 유저 구분(자신도 목록에 추가되었기 때문에 FRIENDS_COUNT_LIMIT 초과인 경우 헤비유저)
		if(friends.size() > User.FRIENDS_COUNT_LIMIT) {
			//헤비유저 동작
			return writeHeavyUserPost(post);
		} else {
			return writeNormalUserPost(post, friends);	
		}
	}
	
	/**
	 * 일반 유저(friend 가 FRIENDS_COUNT_LIMIT 이하)의 경우 파일에 기록
	 * @param post
	 * @param friends
	 */
	private boolean writeNormalUserPost(Post post, List<String> friends) {
		return postDAO.writeNormalUserPost(friends, post);
	}
	
	/**
	 * 헤비 유저(friend 가 FRIENDS_COUNT_LIMIT 이상)의 경우 메모리에 기록
	 * @param post
	 */
	private boolean writeHeavyUserPost(Post post) {
		return postDAO.writeHeavyUserPost(post);
	}

	@Override
	public List<Post> getTimelineList(String userId) {
		List<Post> normalPost = postDAO.getNormalTimelineList(userId);
		List<Post> heavyPost = postDAO.getHeavyTimelineList(userId);
		return sortPost(normalPost, heavyPost);
	}
	
	private List<Post> sortPost(List<Post> list1, List<Post> list2) {
		list1.addAll(list2);
		Comparator<Post> sort = new Comparator<Post>() {

			@Override
			public int compare(Post o1, Post o2) {
				return o2.getCreateTime().compareTo(o1.getCreateTime());
			}

		};
		Collections.sort(list1, sort);
		return list1;
	}
}
