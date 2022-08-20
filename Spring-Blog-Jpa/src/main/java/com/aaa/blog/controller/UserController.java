package com.aaa.blog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aaa.blog.form.LoginForm;
import com.aaa.blog.form.RegisterForm;
import com.aaa.blog.model.User;
import com.aaa.blog.model.UserRole;
import com.aaa.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String index(Model model) {
		List<User> users = this.userService.findAll();
		model.addAttribute("users", users);
		return "/users/view";
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String login(LoginForm loginForm) {
		return "/users/login";
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public String login(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
		if(!userService.authenticate(loginForm.getUsername(), loginForm.getPassword())) {
			bindingResult.rejectValue("username", "login.username", "유저이름이나 암호가 틀립니다.");
			return "/users/login";
		}
		session.setAttribute("username", loginForm.getUsername());
		return "redirect:/index";
	}
	
	@RequestMapping(value="/users/register", method=RequestMethod.GET)
	public String register(RegisterForm registerForm) {
		return "/users/register";
	}
	
	@RequestMapping(value="/users/register", method=RequestMethod.POST)
	public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
		
		Optional<User> optionalUser = this.userService.findByUsername(registerForm.getUsername());
		if(optionalUser.isPresent()) {
			bindingResult.rejectValue("username", "error.user", "User가 이미 존재.");	
		}
		
		/*Optional<User> optionalEmail = this.userService.findByEmail(registerForm.getEmail());
		if(optionalEmail.isPresent()) {
			bindingResult.rejectValue("email", "register.email", "이메일이 이미 존재.");	
		}*/
		
		if(!bindingResult.hasErrors()) {
			User user = new User();
			user.setUsername(registerForm.getUsername());
			//user.setPassword(registerForm.getPassword());
			user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
			user.setEmail(registerForm.getEmail());
			user.setFullname(registerForm.getFullname());
			user.setRole(UserRole.USER);
			this.userService.register(user);
			return "redirect:/home";
		}
		return "/users/register";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
}
