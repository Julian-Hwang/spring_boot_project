package org.zerock.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.domain.MemberVO;

@Controller
public class SampleController {
	
	@GetMapping("/sample1")
	public String sample1(Model model) {
		model.addAttribute("greeting", "Hello World");
		return "index1";
	}
	
	@GetMapping("/sample2")
	public String sample2(Model model) {
		model.addAttribute("intro", "안녕하세요!!!");
		return "index2";
	}
	
	@GetMapping("/sample3")
	public String sample3(Model model) {
		MemberVO vo = new MemberVO(123, "u00", "p00", "Luna", new Timestamp(System.currentTimeMillis()));
		model.addAttribute("vo", vo);
		return "index3";
	}
	
	@GetMapping("/sample4")
	public String sample4(Model model) {
		List<MemberVO> list = new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			list.add(new MemberVO(123, "u00", "p00", "Luna", new Timestamp(System.currentTimeMillis())));
		}
		model.addAttribute("list", list);
		return "index4";
	}
	
	@GetMapping("/sample5")
	public String sample5(Model model) {
		List<MemberVO> list = new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			list.add(new MemberVO(i, "u000"+i%3, "p0000"+i%3, "Luna"+i, new Timestamp(System.currentTimeMillis())));
		}
		model.addAttribute("list", list);
		return "index5";
	}

}
