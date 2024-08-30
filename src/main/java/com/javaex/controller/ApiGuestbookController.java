package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	//리스트
	
	@ResponseBody    //return에 있는 데이터를 json으로 바꿔서 응답문서(response)의 body에 넣어준다.
	@RequestMapping(value="/api/guestbooks/list")
	public List<GuestbookVo> list() {
		
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> personList = guestbookService.exepersonList();
		System.out.println(personList);
		
		return personList;
	}
	
	//글쓰기
	
	@ResponseBody
	@RequestMapping(value="/api/guestbooks/write",method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println(guestbookVo);
		
		
		return "";
		
	}
	
	

}
