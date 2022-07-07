package org.zerock.controller;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.Member;
import org.zerock.domain.MemberRole;
import org.zerock.persistence.MemberRepository;

@Controller
public class SampleController {

	@Autowired
	private MemberRepository repo;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/guest")
	public String forGuest() {
		return "guest";
	}
	
	@RequestMapping("/manager")
	public String forManager() {
		return "manager";
	}
	
	@RequestMapping("/admin")
	public String forAdmin() {
		return "admin";
	}
	
	@PostConstruct
	public void init() { //101명의 사용자를 생성
		for (int i = 0; i <= 100; i++) {
			
			Member member = new Member();
			member.setUid(i);
			member.setUpw("pw"+i);
			member.setUname("사용자"+i);
			
			MemberRole role = new MemberRole();
			if (i<=80) { //user0~user80까지는 BASIC 권한 가짐
				role.setRoleName("BASIC");
			} else if(i<=90) { //user90까지는 MANAGER 권한 가짐
				role.setRoleName("MANAGER");
			} else { //나머지 10명은 ADMIN 권한 가짐
				role.setRoleName("ADMIN");
			}
			member.setRoles(Arrays.asList(role));
			
			repo.save(member);
		}
	}
}