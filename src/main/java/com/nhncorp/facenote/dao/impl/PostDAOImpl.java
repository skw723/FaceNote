package com.nhncorp.facenote.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import com.nhncorp.facenote.HeavyUserPostRepository;
import com.nhncorp.facenote.dao.PostDAO;
import com.nhncorp.facenote.model.Post;

@Repository
public class PostDAOImpl implements PostDAO {

	@Override
	public boolean writeNormalUserPost(List<String> friends, Post post) {
		// TODO 파일에 저장
		for(String friend : friends) {
			BufferedWriter timelineWriter = null;
			try {
				File timeline = new File(friend);
				/* 수정3
				 * RandomAccessFile 을 BufferedWriter로 변경
				 * */
				// 파일이 없을경우 생성 
				if(!timeline.exists()) {
					timeline.createNewFile();
				}

				// post정보 입력(수신자)
				post.setReceiverId(friend);
				timelineWriter = new BufferedWriter(new FileWriter(timeline, true));
				// 기존의 내용 뒤에 추가
				timelineWriter.append(post.toString() + Post.NEW_LINE);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			} finally {
				IOUtils.closeQuietly(timelineWriter);
			}
		}
		return true;
	}

	@Override
	public boolean writeHeavyUserPost(Post post) {
		// TODO 메모리에 저장
		HeavyUserPostRepository.getInstance().addPost(post);
		return true;
	}

	@Override
	public List<Post> getNormalTimelineList(String userId) {
		// TODO 파일 읽기
		List<Post> timelineList = new ArrayList<Post>();
		BufferedReader timeLineReader = null;
		/* 수정4
		 * File 객체 외부로 try문을 빼고 내부 try문 제거
		 * */
		try{
			File timeline = new File(userId);
			/* 수정1
			 * RandomAccessFile 을 BufferedReader로 변경
			 * */
			// 타임라인이 존재할 경우
			if(timeline.exists()){
				timeLineReader = new BufferedReader(new FileReader(timeline));
				/* 수정2
				 * while문 안,밖의 readLine() 중복 제거
				 * */
				String postString;
				while((postString = timeLineReader.readLine()) != null) {
					/* 
					 * splitString[0] 해당 post의 클래스명
					 * splitString[1] post가 등록된 시간 
					 * splitString[2] 작성자 id
					 * splitString[3] 수신자 id
					 * splitString[4] 본문
					 * index5번부터 추가정보들..
					 * */
					String[] splitString = postString.split(Post.FIELD_SEPERATOR);
					try {
						// String[] 타입을 매개변수로 받는 생성자를 찾아서 post 객체 생성
						Class klass = Class.forName(splitString[0]);
						Constructor constructor = klass.getConstructor(new Class[]{String[].class});
						timelineList.add((Post) constructor.newInstance((Object)splitString));
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(timeLineReader);
		}

		return timelineList;
	}

	@Override
	public List<Post> getHeavyTimelineList(String userId) {
		// TODO 메모리 읽기
		List<Post> timelineList = new ArrayList<Post>();
		List<Post> postList = HeavyUserPostRepository.getPosts();
		for(Post post : postList) {
			/* 
			 * 1. post에 저장된 Writer의 친구리스트에 자신의 id가 있을 경우 이거나
			 * 2. Writer가 자신인 경우
			 * */
			if(post.getWriterId().equals(userId) 
					|| post.getWriter().getFriends().contains(userId)){
				timelineList.add(post);
			}
		}
		return timelineList;
	}
}
