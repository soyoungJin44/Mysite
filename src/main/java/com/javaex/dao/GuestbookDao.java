package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired SqlSession sqlSession;

	
	public List<GuestbookVo> personList(){
		
		List<GuestbookVo> personList = sqlSession.selectList("guestbook.selectAll");
		
		System.out.println("/./////////////////////////////////////");
		System.out.println("Dao" + personList);
		
		return personList;
		
	}
	
	//등록
	public int insertPerson(GuestbookVo guestbookVo) {
		System.out.println("dao insert");
		
		System.out.println(guestbookVo);
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		
		System.out.println("count");
		return count;
		
	}
	
	//삭제
	
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("dao 삭제준ㄴ비");
		
		
		int count = sqlSession.delete("guestbook.delete",guestbookVo);
		
		return count;
		
		
	}
}
