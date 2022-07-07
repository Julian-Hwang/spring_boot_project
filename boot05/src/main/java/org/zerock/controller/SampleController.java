package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("/sample1")
	public String sample1(Model model) {
		model.addAttribute("greeting", "Hello World");
		return "sample1";
	}
	
	@GetMapping("/sample2")
	public String sample2(Model model) {
		model.addAttribute("intro", "안녕하세요!!!");
		return "sample2";
	}

}
