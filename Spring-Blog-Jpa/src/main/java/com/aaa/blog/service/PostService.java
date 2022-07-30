package com.aaa.blog.service;

import java.util.List;
import java.util.Optional;

import com.aaa.blog.model.Post;

public interface PostService {
	
	List<Post> findAllOrderedById();
	List<Post> findAllOrderedById(int page);
	Optional<Post> findById(Long id);
	Post create(Post post);
	Post edit(Post post);
	void deleteById(Long id);
}
