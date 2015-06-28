package com.nhncorp.facenote;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.nhncorp.facenote.mybatistest.Client;
import com.nhncorp.facenote.mybatistest.Model;

public class TestMyBatis {
	SqlSessionFactory session;

	@Before
	public void before() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory ss = new SqlSessionFactoryBuilder().build(reader);
		session = ss;
	}

	@Test
	public void test1() {
		SqlSession session = Client.getSessionFactory().openSession();
		List<Model> list = session.selectList("TEST.get");

		session.commit();
		session.close();
	}

	@Test	
	public void test2() {
		try {
			SqlSession s = session.openSession();
			List<Model> list = s.selectList("TEST.get");
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}