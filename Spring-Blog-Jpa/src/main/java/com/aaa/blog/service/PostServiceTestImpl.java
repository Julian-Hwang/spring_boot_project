package com.aaa.blog.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.blog.model.Post;

@Service
public class PostServiceTestImpl implements PostService {
	
	private TagService tagService;
	private UserService userService;
	private List<Post> posts = new ArrayList<Post>();
	
	@Autowired
	public PostServiceTestImpl(UserService userService, TagService tagService) {
		this.userService=userService;
		this.tagService=tagService;
		
		posts.add(new Post(1L, "첫번째 포스트입니다.", "첫번째 포스트의 내용입니다.", new Date(),userService.findById(1L).get(),tagService.getTags()));
		posts.add(new Post(2L, "두번째 포스트입니다.", "두번째 포스트의 내용입니다.", new Date(),userService.findById(1L).get(),tagService.getTags()));
		posts.add(new Post(3L, "세번째 포스트입니다.", "세번째 포스트의 내용입니다.", new Date(),userService.findById(1L).get(),tagService.getTags()));
		posts.add(new Post(4L, "네번째 포스트입니다.", "네번째 포스트의 내용입니다.", new Date(),userService.findById(2L).get(),tagService.getTags()));
		posts.add(new Post(5L, "다섯번째 포스트입니다.", "다섯번째 포스트의 내용입니다.", new Date(),userService.findById(2L).get(),tagService.getTags()));
		posts.add(new Post(6L, "여섯번째 포스트입니다.", "여섯번째 포스트의 내용입니다.", new Date(),userService.findById(3L).get(),tagService.getTags()));
		posts.add(new Post(7L, "일곱번째 포스트입니다.", "일곱번째 포스트의 내용입니다.", new Date(),userService.findById(3L).get(),tagService.getTags()));
		
	}

	@Override
	public List<Post> findAllOrderedById() {
		// TODO Auto-generated method stub
		return posts.stream().sorted(Comparator.comparing(Post::getId)).collect(Collectors.toList());
	}

	@Override
	public List<Post> findAllOrderedById(int page) {
		// TODO Auto-generated method stub
		return posts.stream().sorted(Comparator.comparing(Post::getId)).limit(page).collect(Collectors.toList());
	}

	@Override
	public Optional<Post> findById(Long id) {
		// TODO Auto-generated method stub
		return posts.stream().filter(p->p.getId().equals(id)).findAny();
	}

	@Override
	public Post create(Post post) {
		// TODO Auto-generated method stub
		post.setId(this.posts.stream().count()+1);
		post.setCreatedDate(new Date());
		this.posts.add(post);
		return post;
	}

	@Override
	public Post edit(Post post) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.posts.size(); i++) {
			if (this.posts.get(i).getId() == post.getId()) {
				this.posts.set(i, post);
				return post;
			}
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.posts.size(); i++) {
			if (this.posts.get(i).getId() == id) {
				this.posts.remove(i);
				return;
			}
		}
	}

}
