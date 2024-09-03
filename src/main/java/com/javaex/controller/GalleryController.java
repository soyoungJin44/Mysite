package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GalleryService;

@Controller
public class GalleryController {

	@Autowired
	GalleryService galleryService;
	
	//첫 화면 출력
	@RequestMapping(value="/gallery/main",method= {RequestMethod.GET, RequestMethod.POST})
	public String main() {
		System.out.println("첫화면 출력이용");
		
		return "/gallery/list";
	}
	
	
	
	@RequestMapping(value="/gallery/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert() {
		System.out.println("controller insert()");
		
		
		
		return "";
	}
	
}
