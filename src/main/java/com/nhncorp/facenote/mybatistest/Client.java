package com.nhncorp.facenote.mybatistest;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Client {
	private static SqlSessionFactory sqlMapper = null;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("classpath:mybatis-config.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return sqlMapper;
	}
}
