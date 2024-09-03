package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;

@Controller
public class AttachController {

	@Autowired
	AttachService attachService;
	
	// 메인 form 출력해주기
	@RequestMapping(value="/attach/form", method= {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("dddddd");
		
		return "/attach/form";
	}
	
	//파일 업로드
	
	@RequestMapping(value="/attach/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		System.out.println("upload 기능 준 완");
		System.out.println(file.getOriginalFilename());
	
		String saveName = attachService.exeupload(file);
		System.out.println("controller" +"//////////////"+ saveName);
		
		model.addAttribute("saveName", saveName);
		
		return "/attach/result";
	
	
	}
}
