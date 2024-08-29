package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired BoardDao boardDao;
	
	//list가져오기
	
	public List<BoardVo> exepersonList() {
		System.out.println("service 리스트 가져오기 준비");
		
		 List<BoardVo> personList = boardDao.personList();
		
		return personList;
	}
	
	//읽기
	
	public BoardVo exeread(int no) {
		System.out.println("service read 준비완");
		
		BoardVo boardVo = boardDao.read(no);
		
		return boardVo;
		
	}
	
	//delete
	
	public int exedelete(BoardVo boardVo) {
		System.out.println("service delete 준비완");
		
		int count = boardDao.delete(boardVo);
		System.out.println("service/////"+boardVo);
		return count;
		
		
	}
	
}
