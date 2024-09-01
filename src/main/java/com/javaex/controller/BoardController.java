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
	public String readForm(@ModelAttribute BoardVo boardVo, @RequestParam (value="no") int no, Model model) {
		
		System.out.println("읽기폼 준비완");
		
		BoardVo personVo = boardService.exeread(no);
		System.out.println(personVo);
		
		model.addAttribute("boardVo", personVo);
		
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
		
		return "redirect:/board/list";
		
//		boardService.exedelete();
//		int no = ((PersonVo)session.getAttribute("authUser")).getNo();
		
	}
	
	//수정폼
	
	@RequestMapping(value="/board/modifyform", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam(value="no") int no, Model model) {
		System.out.println("수정 폼 준 완");
		
		
		BoardVo	boardVo = boardService.exemodify(no);
		System.out.println(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return "/board/modifyForm";
	}
	
	
	
	//수정하기
	
	@RequestMapping(value="/board/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo, HttpSession session) {
		
		System.out.println("수정준비완");
		
		PersonVo authUser = (PersonVo) session.getAttribute("authUser");
		int user_no = authUser.getNo();
		
		System.out.println("////////////////////"+user_no);
		
		boardVo.setNo(user_no);
		System.out.println("dddddddd32135145641653"+boardVo);
		
		boardService.exeModifyform(boardVo);
		
		return "redirect:/board/list";
		
	}
	
	//write form
	@RequestMapping(value="/board/writeform", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(HttpSession session) {
		System.out.println("writeform 준 완");
		
		PersonVo authUser = (PersonVo)session.getAttribute("authUser");
		
		
//		boardVo.setNo(uer_no);
//		
//		boardService.
		return "/board/writeForm";
	}
	
	//write
	@RequestMapping(value="/board/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(HttpSession session,@ModelAttribute BoardVo boardVo) {
		System.out.println("write 준 완");
		
		PersonVo user = (PersonVo)session.getAttribute("authUser");
		int user_no = user.getNo();
		
		System.out.println("////////////////"+user_no);
		
		boardVo.setUser_no(user_no);
		
		System.out.println("~~~~~~~~``"+boardVo);
		
		boardService.exeWrite(boardVo);
		
		return "redirect:/board/list";
	}
	
	
	

}
