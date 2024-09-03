package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class AttachDao {
	@Autowired
	SqlSession sqlSession;
	
	//파일 업로드
	
	public String upload(MultipartFile file) {
		System.out.println("dao upload 준 완");

		
		
		return "";
	}
	
}
