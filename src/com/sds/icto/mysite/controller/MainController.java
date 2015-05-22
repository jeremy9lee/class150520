package com.sds.icto.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	
	@RequestMapping("/main.do")
	public String goToMain(){
		System.out.println("들어옴");
		return "/WEB-INF/views/main/index.jsp";
	}
	
	
	
}
