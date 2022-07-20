package com.aaa.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaa.blog.service.HelloworldService;

@Controller
public class HelloworldController {
	
	@Autowired
	HelloworldService helloworldService;
	
	@RequestMapping("/hello")
	public String helloIndex(Model model) {
		String helloMessage = helloworldService.helloworld();
		model.addAttribute("message", helloMessage);
		
		return "hello";
	}

}
