package org.zerock.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Secured({"ADMIN", "MANAGER"})
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/success")
	public String success() {
		return "success";
	}
	
	@Secured({"ADMIN", "MANAGER"})
	@RequestMapping("/managerSecret")
	public String managerSecret() {
		return "managerSecret";
	}
	
	@Secured({"ADMIN"})
	@RequestMapping("/adminSecret")
	public String adminSecret() {
		return "adminSecret";
	}
	
}