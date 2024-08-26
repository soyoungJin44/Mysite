package com.javaex.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired GuestbookService guestbookService;
	
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
		
		
		return "redirect:/guestbook/list";
		
	}

}
