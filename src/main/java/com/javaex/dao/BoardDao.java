package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired SqlSession sqlSession;
	
	//리스트 가져오기
	
	public List<BoardVo> personList() {
		System.out.println("dao 리스트 가져올준비");
		
		 List<BoardVo> personList = sqlSession.selectList("board.selectAll");
		
//		 System.out.println(personList);
		return personList;
		
	}
	
	//읽기
	
	public BoardVo read(int no) {
		System.out.println("Dao read준ㅇ비완");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectOne",no);
		
		return boardVo;
		
	}
	
	//delete
	public int delete(BoardVo boardVo) {
		System.out.println("dao delete 준비");
		System.out.println("dao>>>>>" + boardVo);
		
		int count = sqlSession.delete("board.delete", boardVo);
		
		return count;
	}

}
