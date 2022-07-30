package com.aaa.blog.controller;



import java.util.Comparator;
import java.util.Date;
import java.util.List;
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

import com.aaa.blog.form.PostForm;
import com.aaa.blog.model.Post;
import com.aaa.blog.model.User;
import com.aaa.blog.service.PostService;
import com.aaa.blog.service.TagService;
import com.aaa.blog.service.UserService;

@Controller
public class PostController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/posts/create", method=RequestMethod.GET)
	public String newPost(PostForm postForm) {
		return "/posts/create";
	}
	
	@RequestMapping(value="/posts/create", method=RequestMethod.POST)
	public String newPost(@Valid PostForm postForm, BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			bindingResult.rejectValue("body", "create.body", "포스트 생성 시 오류가 발생했습니다.");
			return "/posts/create";
		}
		
		Post post = new Post();
		post.setId(postService.findAllOrderedById().stream().map(Post::getId).max(Comparator.naturalOrder()).orElse(Long.MIN_VALUE)+1);
		post.setTitle(postForm.getTitle());
		post.setBody(postForm.getBody());
		post.setCreatedDate(new Date());
		
		post.setUser(userService.findByUsername((String)session.getAttribute("username")).get());
		
		Post p = postService.create(post);
		
		if(p==null) {
			return "/errors/default";
		}
		
		return "redirect:/posts";
	}
	
	@RequestMapping(value="/posts/view/{id}",method=RequestMethod.GET)
	public String viewPostwithId(@PathVariable("id") Long id, Model model) {
		Optional<Post> optionalPost = postService.findById(id);
		if (optionalPost.isPresent()) {
			model.addAttribute("post", optionalPost.get());
			
			return "/posts/view";
		}
		return "/errors/404";
	}
	
	@RequestMapping(value="/posts/viewUser/{username}", method = RequestMethod.GET)
	public String viewPostwithUsername(@PathVariable("username") String username, Model model) {
		Optional<User> optionalUser = userService.findByUsername(username);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			List<Post> posts = postService.findAllOrderedById();
			
			model.addAttribute("posts", posts);
			model.addAttribute("user", user);
			
			return "/posts";
		} else {
			return "/errors/404";
		}
	}
	
	@RequestMapping(value="/posts/edit/{id}", method = RequestMethod.GET)
	public String editPost(@PathVariable("id") Long id, PostForm postForm) {
		Optional<Post> optionalPost = postService.findById(id);
		
		if(optionalPost.isPresent()) {
			postForm.setTitle(optionalPost.get().getTitle());
			postForm.setBody(optionalPost.get().getBody());
			return "/posts/create";
		}
		
		return "/errors/404";
	}
	
	@RequestMapping(value="/posts/edit/{id}", method = RequestMethod.POST)
	public String editPost(@PathVariable("id") Long id, @Valid PostForm postForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.rejectValue("body", "edit.body", "포스트 수정 시 오류발생.");
			return "/posts/create";
		}
		
		Optional<Post> optionalPost = postService.findById(id);
		
		if(optionalPost.isPresent()) {
			Optional<User> optionalUser = userService.findById(optionalPost.get().getUser().getId());
			
			if (optionalUser.isPresent()) {
				Post post = optionalPost.get();
				post.setTitle(postForm.getTitle());
				post.setBody(postForm.getBody());
				post.setCreatedDate(new Date());
				post.setUser(optionalUser.get());
				post.setTags(tagService.getTags());
				
				Post p = postService.edit(post);
				
				if (p==null) {
					return "/posts/create";
				}
			}
			return "redirect:/posts";
		}
		return "/errors/404";
	}
	
	@RequestMapping(value="/posts/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		Optional<Post> optionalPost = postService.findById(id);
		
		if(optionalPost.isPresent()) {
			postService.deleteById(id);
		}
		
		return "redirect:/posts";
	}
	
	
	public String index(Model model) {
		List<Post> posts = postService.findAllOrderedById();
		model.addAttribute("posts", posts);
		
		return "/index";
	}
}
