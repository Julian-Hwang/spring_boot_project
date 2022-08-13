package com.aaa.blog.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaa.blog.model.User;
import com.aaa.blog.service.UserService;

@RestController
@RequestMapping(value="/rest/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/getAll")
	//@GetMapping(value="/getAll", produces = {"application/xml","text/xml"}, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<User>> getAllPost(){
		List<User> users = this.userService.findAll();
		
		if (users == null || users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<Boolean> authenticate(@RequestBody Map<String, String> params, HttpSession session){
		Boolean blogin = userService.authenticate(params.get("username"), params.get("password"));
		if(!blogin) {
			return new ResponseEntity<Boolean>(blogin, HttpStatus.FORBIDDEN);
		}
		
		session.setAttribute("username", params.get("username"));
		return new ResponseEntity<Boolean>(blogin, HttpStatus.OK);
	}
}
