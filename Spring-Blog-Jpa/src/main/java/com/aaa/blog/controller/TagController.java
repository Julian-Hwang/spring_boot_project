package com.aaa.blog.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aaa.blog.model.Post;
import com.aaa.blog.model.Tag;
import com.aaa.blog.service.PostService;
import com.aaa.blog.service.TagService;
import com.aaa.blog.service.UserService;

@Controller
public class TagController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/tags/create", method=RequestMethod.POST)
	public String newTag(@Valid Tag tag, BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			bindingResult.rejectValue("body", "tag.body", "댓글 생성시 오류 발생.");
			return "/tags/create";
		}
		
		if(session.getAttribute("username")==null) {
			bindingResult.rejectValue("body", "tag.username", "로그인 하세요.");
			return "/error";
		}
		
		tag.setId(tagService.getTags().stream().map(Tag::getId).max(Comparator.naturalOrder()).orElse(Long.MIN_VALUE)+1);
		tag.setUser(userService.findByUsername((String)session.getAttribute("username")).get());
		tag.setCreatedDate(new Date());
		tagService.save(tag);
		
		return "redirect:/posts/view/"+tag.getPost().getId();
	}
	
	@RequestMapping(value="/tags/view/{id}", method = RequestMethod.GET)
	public String postTagwithId(@PathVariable Long id, Model model) {
		Optional<Post> optionalPost = postService.findById(id);
		
		if(optionalPost.isPresent()) {
			Tag tag = new Tag();
			tag.setPost(optionalPost.get());
			tag.setCreatedDate(new Date());
			
			model.addAttribute("tag", tag);
			return "/tags/create";
		} else {
			return "/error";
		}
	}
}
