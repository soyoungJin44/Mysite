package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.PersonVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	@Autowired BoardService boardService;
	
	
	//list
	@RequestMapping(value="/board/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list 가져오기");
		
		List<BoardVo> personList = boardService.exepersonList();
		
//		System.out.println(personList);
		model.addAttribute("personList", personList);
		
		return "/board/list";
	}
	
	//읽기폼
	@RequestMapping(value="/board/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String readForm(@ModelAttribute BoardVo boardVo, @RequestParam (value="no") int no) {
		
		System.out.println("읽기폼 준비완");
		
		boardService.exeread(no);
		
		
		return "/board/read";
		
	}
	
	
//	//삭제
	@RequestMapping(value="/board/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm (HttpSession session,@ModelAttribute BoardVo boardVo) {
		System.out.println("딜리트 준비");
		
		System.out.println(boardVo);
		
		// session에 있는 user no가져오는 작업
		PersonVo authUser = (PersonVo)session.getAttribute("authUser");
		int user_no = authUser.getNo();
		boardVo.setUser_no(user_no);
		System.out.println(boardVo);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>");
		//보내주기
		boardService.exedelete(boardVo);
		
		
//		boardService.exedelete();
//		int no = ((PersonVo)session.getAttribute("authUser")).getNo();
		
		
		
		return "redirect:/board/list";
		
	}
	
	
	

}
