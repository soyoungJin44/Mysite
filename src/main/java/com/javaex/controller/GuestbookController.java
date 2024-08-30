package com.javaex.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired 
	private GuestbookService guestbookService;
	
	//리스트 
	
	@RequestMapping(value="/guestbook/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		System.out.println("list용");
		
		List<GuestbookVo> personList = guestbookService.exepersonList();
		System.out.println("controller" + personList);
		
		model.addAttribute("personList", personList);
		return "/guestbook/addlist";
		
	}
	
	//등록
	
	@RequestMapping(value="/guestbook/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
	
		System.out.println("insert용");
		
		guestbookService.exeInsert(guestbookVo);
		
		System.out.println("controller"+ guestbookVo);
		
		return "redirect:/guestbook/list";
	}
	
	//삭제폼
	@RequestMapping(value="/guestbook/deleteform", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		
		System.out.println("deleteform이용");
		
		return "/guestbook/deleteForm";
	}
	
	//삭제 기능
	@RequestMapping(value="/guestbook/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("delete 기능이용");
		
		System.out.println("=====================");
		System.out.println(guestbookVo);
		int count = guestbookService.exeDelete(guestbookVo);
		
//		System.out.println("/////////sakfhdfashfjshkl" + count);
		
		if(count > 0) {
			return "redirect:/guestbook/list";
		}else {
			return "redirect:/guestbook/deleteform";
			
		}
		
	}
	
	//################ ajaxindex 방명록 ####### script ###############
	
	@RequestMapping(value="/guestbook/ajaxindex", method= {RequestMethod.GET, RequestMethod.POST})
	public String ajaxindex() {
		
		System.out.println("ajaxindex 준비완");
		
		//방명록 데이터 가져오지않는다 >> 초반 폼만 한번 출력시켜주고, 데이터 받고 그걸 스크립트에 넘겨서 스크립트에서 ㅇㅇㅇㅇㅇ
		
		return null;
		
	}
	

}
		
	
