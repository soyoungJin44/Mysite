package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.PersonVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserCotroller {
	
	@Autowired
	private UserService userService;
	
	//필드
	//생성자
	//메서드 gs
	//메서드 일반

	
	//회원가입 폼
	@RequestMapping (value="/joinform", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		
		System.out.println("joinForm");
		
		return "/user/joinForm";
	}
	

	//회원가입 기능
	@RequestMapping (value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insertPerson(@ModelAttribute PersonVo personVo) {
		
		System.out.println(personVo);
		
		userService.exeInsertPerson(personVo);
		
		return "/user/joinOk";
		
		
	}
	
	//로그인성공폼(join Ok)
	
	@RequestMapping(value="/joinOk", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinOk() {
		System.out.println("joinOk 폼이용");
		
		
		return "/user/joinOk";
	}
	
	//로그인 폼이용
	@RequestMapping(value="/loginform", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("loginForm이용");
		
		return "/user/loginForm";
		
	}
	
	//로그인 기능
	
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute PersonVo personVo, HttpSession session) {
		
		System.out.println("로그인 기능이용");
		System.out.println(personVo);
		
		
		PersonVo authUser = userService.exeSelectOne(personVo);
		
		
		session.setAttribute("authUser", authUser);
		System.out.println(authUser);
		
//		return "redirect:/main";
		if(authUser == null) {
			return "user/loginForm";
			
		}else {
			return "redirect:/main";
		}
		
	}
	
	//로그아웃기능
	@RequestMapping(value="/user/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		
		System.out.println("로그아웃이용");
		
//		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
		
	}
	
	//수정폼
	
	@RequestMapping(value="/user/modifyform", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		
		System.out.println("수정 폼이용");
		
		PersonVo authUser = (PersonVo)session.getAttribute("authUser");
		//로그인한 사용자의 번호
		int no = authUser.getNo();
		
		PersonVo personVo = userService.getPersonOne(no);
		System.out.println("11111 "+personVo);
		
		model.addAttribute("id", personVo);
		return "/user/modifyForm";
		
	}
	
	//수정 기능
	
	@RequestMapping(value="/user/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo personVo, HttpSession session) {
		
		System.out.println("수정 기능이용");
		
		PersonVo npersonVo = userService.exeupdatePerson(personVo);
		System.out.println("수정"+personVo);
		
		session.setAttribute("authUser", npersonVo);
		
		return "redirect:/main";
	}
	
	// ajax 중복확인
	@ResponseBody
	@RequestMapping(value="/api/check", method= {RequestMethod.GET, RequestMethod.POST})
	public boolean check(String id) {
		System.out.println("기존 컨트롤러 쳌");
		System.out.println(id);
		boolean result = userService.execheck(id);
		
		System.out.println(result);
		
		return result;
	}
	
	
	
}
