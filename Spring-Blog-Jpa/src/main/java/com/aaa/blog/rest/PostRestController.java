package com.aaa.blog.rest;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaa.blog.model.Post;
import com.aaa.blog.model.User;
import com.aaa.blog.service.PostService;
import com.aaa.blog.service.TagService;
import com.aaa.blog.service.UserService;

@RestController
@RequestMapping("/rest/post")
public class PostRestController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("getAll")
	//@GetMapping(value = "getAll", produces = {"application/xml", "text/xml"}, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Post>> getAllPost(){
		List<Post> posts = this.postService.findAllOrderedById();
		if (posts == null || posts.isEmpty()) {
			return new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("get/{id}")
	//@GetMapping(value="get/{id}", produces = {"application/xml","text/xml"}, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Post> getPostById(@PathVariable("id")long id){
		Optional<Post> optionalPost = this.postService.findById(id);
		if(optionalPost.isPresent()) {
			return new ResponseEntity<Post>(optionalPost.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("create")
	public ResponseEntity<Post> create(@RequestBody Post post, HttpSession session){
		post.setId(postService.findAllOrderedById().stream().map(Post::getId).max(Comparator.naturalOrder()).orElse(Long.MIN_VALUE)+1);
		post.setUser(userService.findByUsername((String)session.getAttribute("username")).get());
		Post p = this.postService.create(post);
		
		if(p==null) {
			return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Post>(p, HttpStatus.OK);
	}
	
	@PutMapping("put/{id}")
	public ResponseEntity<Post> update(@PathVariable("id") long id, @RequestBody Post post){
		Optional<Post> optionalPost = postService.findById(id);
		
		if(optionalPost.isPresent()) {
			Optional<User> optionalUser = userService.findById(optionalPost.get().getUser().getId());
			
			if(optionalUser.isPresent()) {
				Post updatePost = optionalPost.get();
				updatePost.setId(id);
				updatePost.setTitle(post.getTitle());
				updatePost.setBody(post.getBody());
				updatePost.setCreatedDate(new Date());
				updatePost.setUser(optionalUser.get());
				updatePost.setTags(tagService.getTags());
				
				Post p = postService.edit(post);
				
				if(p==null) {
					return new ResponseEntity<Post>(post, HttpStatus.INTERNAL_SERVER_ERROR);
				}	
				return new ResponseEntity<Post>(post, HttpStatus.OK);
			}
			return new ResponseEntity<Post>(post, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Post>(post, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id){
		Optional<Post> optionalPost = postService.findById(id);
		
		if (optionalPost.isPresent()) {
			postService.deleteById(id);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
