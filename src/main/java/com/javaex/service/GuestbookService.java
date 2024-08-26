package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired GuestbookDao guestbookDao;
	
	//리스트
	public List<GuestbookVo> exepersonList(){
		
		List<GuestbookVo> exepersonList = guestbookDao.personList();
		System.out.println("service" + exepersonList);
		
		return exepersonList;
	}
	
	//등록
	
	public int exeInsert(GuestbookVo guestbookVo) {
		System.out.println("service insert");
		
		int count = guestbookDao.insertPerson(guestbookVo);
		
		return count;
	}
	
	//삭제
	
	public int exeDelete(GuestbookVo guestbookVo) {
		System.out.println("service 삭제용");
		
		int count = guestbookDao.delete(guestbookVo);
		
		return count;
		
		
	}

}
