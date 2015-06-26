package com.nhncorp.facenote;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.nhncorp.facenote.mybatistest.Client;
import com.nhncorp.facenote.mybatistest.Model;

public class TestMyBatis {
	@Test
	public void test1() {
		SqlSession session = Client.getSessionFactory().openSession();
		List<Model> list = session.selectList("TEST.get");
		
		session.commit();
		session.close();
	}
}