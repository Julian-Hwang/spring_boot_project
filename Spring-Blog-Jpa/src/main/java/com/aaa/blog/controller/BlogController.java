package com.aaa.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaa.blog.model.Post;
import com.aaa.blog.service.PostService;

@Controller
public class BlogController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value= {"/", "/index"})
	public String blog(@RequestParam(defaultValue = "20") int page, Model model) {
		List<Post> posts = postService.findAllOrderedById(page);
		model.addAttribute("posts", posts);
		
		return "/index";
	}

}
