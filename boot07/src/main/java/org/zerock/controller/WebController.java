package org.zerock.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.domain.MemberVO;
import org.zerock.persistence.MemberVORepository;

@Controller
public class WebController {
	
	@Autowired
	private MemberVORepository repo;
	
	@PostConstruct
	public void init() {
		IntStream.range(1, 10).forEach(i->{
			MemberVO member = new MemberVO();
			member.setMno(Long.valueOf(i));
			member.setMid("user"+i);
			member.setMpw("pw"+i);
			member.setMname("사용자"+i);
			member.setRegdate(new Timestamp(System.currentTimeMillis()));
			
			repo.save(member);
		});
	}
	
	@GetMapping("/home")
	public String test(Model model) {
		List<MemberVO> list = repo.findAll();
		model.addAttribute("list", list);
		
		return "index";
	}

}
